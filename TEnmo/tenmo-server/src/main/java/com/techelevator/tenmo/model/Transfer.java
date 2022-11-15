package com.techelevator.tenmo.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class Transfer {

    public Transfer(Integer transferId){
        this.transferId = transferId;
    }
    public Transfer() {
        transferId = 0;
    }

    private int accountTo;

    private int accountFrom;

    private BigDecimal amount;

    private int transferId;

    //private boolean successful;

    private int transferTypeId;

    private int transferStatusId;

    private int userId;

    //set 'em and get 'em

    public int getUserId(){return userId;}
    public void setUserId(int userId){this.userId = userId;}


    public int getAccountTo(){
        return accountTo;
    }
    public void setAccountTo(int accountTo){
        this.accountTo = accountTo;
    }



    public int getAccountFrom(){
        return accountFrom;
    }
    public void setAccountFrom(int accountFrom) {
        this.accountFrom = accountFrom;
    }



    public BigDecimal getAmount(){
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }



    public int getTransferId(){
        return transferId;
    }
    public void setTransferId(int transferId){
        this.transferId = transferId;
    }

//    public boolean isSuccessful(){
//        return successful;
    //}
    public int getTransferTypeId(){
        return transferTypeId;
    }
    public void setTransferTypeId(int transferTypeId){
        this.transferTypeId = transferTypeId;
    }
    public int getTransferStatusId(){
        return transferStatusId;
    }
    public void setTransferStatusId(int transferStatusId){
        this.transferStatusId = transferStatusId;
    }
}
