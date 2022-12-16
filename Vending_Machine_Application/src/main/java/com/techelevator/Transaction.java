package com.techelevator;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
// output/format for sales log =  " currentDate + currentTime + AM/PM + transactionType ( feed / name of item+location / give change)
    // amount deposited/spent/change + amount of new balance post-prior-change.



    // Properties
    private SimpleDateFormat dateTime = new SimpleDateFormat("MM/dd/yyyy hh.mm.ss aa");
    private String transactionType;
    private double currentBalance;
    private Product dispensedItem;
    private String currentTime = dateTime.format(new Date()).toString();
    double fedMoney;




    // Constructors
        public Transaction (Product dispensedItem){
            this.currentTime = currentTime;
            this.dispensedItem = dispensedItem;
            this.currentBalance = currentBalance;
        }


    // G&S

    // Methods




}
