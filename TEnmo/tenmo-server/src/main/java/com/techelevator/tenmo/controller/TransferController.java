package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@PreAuthorize("permitAll")
@RestController
public class TransferController {
    private TransferDao transferDao;
    public TransferController(TransferDao transferDao) {
        this.transferDao = transferDao;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "transfer", method = RequestMethod.POST)
    Transfer create(@RequestBody Transfer transfer){
        return transferDao.createTransfer(transfer);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "transfer", method = RequestMethod.GET)
    public Transfer[] transferList (){
        Transfer[] transferList = transferDao.transferList();
        return transferList;
    }

    @RequestMapping(path = "transfer/transfer_status/{transferId}",method = RequestMethod.GET)
    public String getTransferStatusByTransferId (@PathVariable int transferId) {
        String transferStatus = transferDao.getTransferStatusById(transferId);
        return transferStatus;
    }
    @RequestMapping(path = "transfer/transfer_type/{transferId}",method = RequestMethod.GET)
    public String getTransferTypeByTransferId (@PathVariable int transferId) {
        String transferType = transferDao.getTransferTypeById(transferId);
        return transferType;
    }



}
