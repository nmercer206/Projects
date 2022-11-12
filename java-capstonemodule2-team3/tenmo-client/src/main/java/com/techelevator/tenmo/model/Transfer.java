package com.techelevator.tenmo.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Transfer {


    public Transfer(int accountFromId, int accountToId, BigDecimal amount) {
        accountTo = accountToId;
        accountFrom = accountFromId;
        this.amount = amount;
    }
    public Transfer(){

    }

    private int userId;

    public int getUserId(){
        return userId;
    }




    private int accountTo;

    private int accountFrom;

    private BigDecimal amount;

    private int transferId;

    private boolean successful;

    private int transferTypeId;

    private int transferStatusId;

    private String transferStatus;

    private String transferType;

    //set 'em and get 'em

    public int getAccountTo() {
        return accountTo;
    }

    public int getAccountFrom() {
        return accountFrom;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public int getTransferId() {
        return transferId;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public int getTransferTypeId() {
        return transferTypeId;
    }

    public int getTransferStatusId() { return transferStatusId;}

    public String getTransferStatus() { return transferStatus;}

    public String getTransferType() {return transferStatus;}

    public void setTransferTypeId(int typeId) {
        transferTypeId = typeId;
    }

    public void setTransferStatusId(int statusId) {
        transferStatusId = statusId;
    }
}
