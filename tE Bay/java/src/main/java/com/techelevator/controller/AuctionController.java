package com.techelevator.controller;

import com.techelevator.dao.AuctionDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class AuctionController {

    @Autowired
    private AuctionDao dao;
    @Autowired
    private UserDao userDao;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/auctions", method = RequestMethod.POST)
    public Auction createAuction(@RequestBody CreateAuctionDto dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        int userId = userDao.findIdByUsername(username);
        if (userId == dto.getOwnerId()) {
            return dao.createAuction(dto);
        }
        else {
            throw new AuthenticationException("User not authorized to create this auction") {};
        }
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/auctions", method = RequestMethod.GET)
    public List<ListAuctionDto> getAuctions() {
        return dao.getAll();
    }

    @RequestMapping(path = "/auctions/{id}", method = RequestMethod.GET)
    public Auction getAuction(@PathVariable int id) {
        return dao.getAuctionById(id);
    }

    @RequestMapping(path = "/auctions/{id}", method = RequestMethod.PUT)
    public Auction updateAuction(@RequestBody UpdateAuctionDto dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        int userId = userDao.findIdByUsername(auth.getName());
        dto.setOwnerId(userId); // Ignore this
        if (userId == dto.getOwnerId()) {
            return dao.updateAuction(dto);
        }
        else {
            throw new AuthenticationException("User not authorized to edit this auction") {};
        }
    }

    @RequestMapping(path = "/auctions/{id}", method = RequestMethod.DELETE)
    public void deleteAuctionById(@PathVariable int id) {
        Auction auction = dao.getAuctionById(id);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        int userId = userDao.findIdByUsername(auth.getName());
        if (userId == auction.getOwner().getId()) {
            dao.deleteAuctionById(id);
        }
        else {
            throw new AuthenticationException("User not authorized to delete this auction") {};
        }
    }
}
