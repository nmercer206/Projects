package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class BalanceController {

    private AccountDao accountDao;
        public BalanceController(AccountDao accountDao) {
            this.accountDao = accountDao;
        }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "account/{accountId}", method = RequestMethod.PUT)
    public Account updateAccount(@RequestBody Account account, @PathVariable int accountId){
        Account updatedAccount = accountDao.updateAccount(account);
        if (updatedAccount == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account with id: " + accountId + " doesn't exist.");
        } else {
            return updatedAccount;
        }
    }

    }
