package com.techelevator.dao;

import com.techelevator.model.Auction;
import com.techelevator.model.CreateAuctionDto;
import com.techelevator.model.ListAuctionDto;
import com.techelevator.model.UpdateAuctionDto;

import java.util.List;

public interface AuctionDao {

    Auction createAuction(CreateAuctionDto dto);

    List<ListAuctionDto> getAll();

    Auction getAuctionById(int id);

    Auction updateAuction(UpdateAuctionDto dto);

    void deleteAuctionById(int id);


}
