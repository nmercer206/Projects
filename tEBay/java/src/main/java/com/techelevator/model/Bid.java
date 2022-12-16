package com.techelevator.model;

import java.math.BigDecimal;

public class Bid {

    private int id;
    private int auctionId;
    private int userId;
    private String username;
    private BigDecimal bidAmount;

    public Bid() {
    }

    public Bid(int id, int auctionId, int userId, String username, BigDecimal bidAmount) {
        this.id = id;
        this.auctionId = auctionId;
        this.userId = userId;
        this.username = username;
        this.bidAmount = bidAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BigDecimal getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(BigDecimal bidAmount) {
        this.bidAmount = bidAmount;
    }

    @Override
    public String toString() {
        return "Bid{" +
                "id=" + id +
                ", auctionId=" + auctionId +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", bidAmount=" + bidAmount +
                '}';
    }
}
