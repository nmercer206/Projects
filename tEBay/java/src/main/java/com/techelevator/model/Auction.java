package com.techelevator.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Auction {

    private int id;
    private User owner;
    private String title;
    private String description;
    private BigDecimal startingPrice;
    private Bid winningBid;
    private Date startDate;
    private Date endDate;
    private List<Bid> bids;
    private String imagePath;

    public Auction() {
    }

    public Auction(int id, User owner, String title, String description, BigDecimal startingPrice, Bid winningBid, Date startDate, Date endDate, List<Bid> bids, String imagePath) {
        this.id = id;
        this.owner = owner;
        this.title = title;
        this.description = description;
        this.startingPrice = startingPrice;
        this.winningBid = winningBid;
        this.startDate = startDate;
        this.endDate = endDate;
        this.bids = bids;
        this.imagePath = imagePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
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

    public Bid getWinningBid() {
        return winningBid;
    }

    public void setWinningBid(Bid winningBid) {
        this.winningBid = winningBid;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Auction{" +
                "id=" + id +
                ", owner=" + owner +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startingPrice=" + startingPrice +
                ", winningBid=" + winningBid +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", bids=" + bids +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
