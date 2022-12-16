package com.techelevator.model;

import java.math.BigDecimal;
import java.util.Date;

public class CreateAuctionDto {

    private int ownerId;
    private String title;
    private String description;
    private BigDecimal startingPrice;
    private int auctionTypeId;
    private Date endDate;
    private String imagePath;

    public CreateAuctionDto() {
    }

    public CreateAuctionDto(int ownerId, String title, String description, BigDecimal startingPrice, int auctionTypeId, Date endDate, String imagePath) {
        this.ownerId = ownerId;
        this.title = title;
        this.description = description;
        this.startingPrice = startingPrice;
        this.auctionTypeId = auctionTypeId;
        this.endDate = endDate;
        this.imagePath = imagePath;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(BigDecimal startingPrice) {
        this.startingPrice = startingPrice;
    }

    public int getAuctionTypeId() {
        return auctionTypeId;
    }

    public void setAuctionTypeId(int auctionTypeId) {
        this.auctionTypeId = auctionTypeId;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "CreateAuctionDto{" +
                "ownerId=" + ownerId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startingPrice=" + startingPrice +
                ", auctionTypeId=" + auctionTypeId +
                ", endDate=" + endDate +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
