package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@RestController
@PreAuthorize("permitAll")
public class AccountController {
    private UserDao userDao;

    public AccountController(UserDao userDao) {
        this.userDao = userDao;
    }

    // Made getBalance
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "account/{accountId}/balance", method = RequestMethod.GET)
    public BigDecimal getBalance(@PathVariable int accountId) {
        BigDecimal balance = userDao.getBalanceById(accountId);
        return balance;
    }

    @RequestMapping(path = "{userId}", method = RequestMethod.GET)
    public User user(@PathVariable int userId) {
        User user = userDao.getUserById(userId);
        return user;
    }


    @RequestMapping(path = "account", method = RequestMethod.GET)
    public List<User> userList() {
        return userDao.findAll();
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "account/{username}", method = RequestMethod.GET)
    public int idByUsername(@PathVariable String username) {
        int accountId = userDao.getIdByUsername(username);
        return accountId;
    }

    @RequestMapping(path = "account/username/{accountId}", method = RequestMethod.GET)
    public String getUserNameByAccountId(@PathVariable int accountId) {
        String userName = userDao.getUserNameByAccountId(accountId);
        return userName;
    }


    @RequestMapping(path = "tenmo_user/{username}", method = RequestMethod.GET)
    public int findByUsername(@PathVariable String username) {
        int userId = userDao.findIdByUsername(username);
        return userId;

    }
}

    //TransferTo
//    @RequestMapping(path = "account/{username}/transfer", method = RequestMethod.PUT)
//    public void transferAmount (@RequestBody String transferName, BigDecimal transferAmount, @PathVariable int fromId){
//        userDao.transferTo(fromId, transferName, transferAmount);
//    }








