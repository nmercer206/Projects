package com.techelevator.controller;

import com.techelevator.dao.BidDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Bid;
import com.techelevator.model.CreateBidDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class BidController {

    @Autowired
    private BidDao dao;
    @Autowired
    private UserDao userDao;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/bids", method = RequestMethod.POST)
    public Bid createBid(@RequestBody CreateBidDto dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        int userId = userDao.findIdByUsername(auth.getName());
        if (userId == dto.getUserId()) {
            return dao.createBid(dto);
        }
        else {
            throw new AuthenticationException("User not authorized to create this bid") {};
        }
    }

    @RequestMapping(path = "/bids/{id}", method = RequestMethod.GET)
    public Bid getBidById(@PathVariable int id) {
        return dao.getBidById(id);
    }

    @RequestMapping(path = "/highbid/{id}", method = RequestMethod.GET)
    public BigDecimal getHighestBidAmountByAuctionId(@PathVariable int id) {
        return dao.getHighestBidAmountByAuctionId(id);
    }
}
