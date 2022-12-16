package com.techelevator.model;

import java.math.BigDecimal;

public class CreateBidDto {

    private int auctionId;
    private int userId;
    private BigDecimal bidAmount;

    public CreateBidDto() {
    }

    public CreateBidDto(int auctionId, int userId, BigDecimal bidAmount) {
        this.auctionId = auctionId;
        this.userId = userId;
        this.bidAmount = bidAmount;
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

    public BigDecimal getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(BigDecimal bidAmount) {
        this.bidAmount = bidAmount;
    }

    @Override
    public String toString() {
        return "CreateBidDto{" +
                "auctionId=" + auctionId +
                ", userId=" + userId +
                ", bidAmount=" + bidAmount +
                '}';
    }
}
