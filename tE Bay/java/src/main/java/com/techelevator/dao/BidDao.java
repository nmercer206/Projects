package com.techelevator.dao;

import com.techelevator.model.Bid;
import com.techelevator.model.CreateBidDto;

import java.math.BigDecimal;
import java.util.List;

public interface BidDao {

    Bid createBid(CreateBidDto dto);

    Bid getBidById(int id);

    BigDecimal getHighestBidAmountByAuctionId(int id);

    List<Bid> getBidsByAuctionId(int id);
}
