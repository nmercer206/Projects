package com.techelevator;

public class User {

    //properties

    private double balance;


    public User(){
        this.balance=0;
    }


    public double getBalance(){
        return balance;
    }

    //methods

    public void increaseBalance(double fedMoney){
        balance += fedMoney;
    }

    public void subtractBalance(double amountToSubtract){
        balance -= amountToSubtract;
    }



}
