package com.techelevator.model;

import java.math.BigDecimal;
import java.util.Date;

public class ListAuctionDto {

    private int id;
    private String title;
    private String ownerName;
    private String imagePath;
    private BigDecimal startingPrice;
    private BigDecimal highestBid;
    private Date endDate;

    public ListAuctionDto() {
    }

    public ListAuctionDto(int id, String title, String ownerName, String imagePath, BigDecimal startingPrice, BigDecimal highestBid, Date endDate) {
        this.id = id;
        this.title = title;
        this.ownerName = ownerName;
        this.imagePath = imagePath;
        this.startingPrice = startingPrice;
        this.highestBid = highestBid;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public BigDecimal getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(BigDecimal startingPrice) {
        this.startingPrice = startingPrice;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getHighestBid() {
        return highestBid;
    }

    public void setHighestBid(BigDecimal highestBid) {
        this.highestBid = highestBid;
    }

    @Override
    public String toString() {
        return "AuctionListDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", startingPrice=" + startingPrice +
                ", endDate=" + endDate +
                '}';
    }
}
