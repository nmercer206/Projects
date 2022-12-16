package com.techelevator.dao;

import com.techelevator.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class JdbcAuctionDao implements AuctionDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Auction createAuction(CreateAuctionDto dto) {
        String sql = "INSERT INTO auction (owner_id, title, description, starting_price, start_date, end_date, " +
                "image_path) " +
                "VALUES (?,?,?,?,NOW()::timestamp,to_timestamp(?, 'YYYY-MM-DD HH24:MI:SS'),?) " +
                "RETURNING auction_id";
        int newId = jdbcTemplate.queryForObject(sql, int.class, dto.getOwnerId(), dto.getTitle(), dto.getDescription(),
                dto.getStartingPrice(), dateFormat.format(dto.getEndDate()), dto.getImagePath());
        return getAuctionById(newId);
    }

    @Override
    public List<ListAuctionDto> getAll() {
        List<ListAuctionDto> auctions = new ArrayList<>();
        String sql = "SELECT auction.auction_id, title, MAX(users.username) AS username, image_path, starting_price, " +
                "end_date, MAX(bid.amount) AS highest_bid " +
                "FROM auction " +
                "JOIN users ON owner_id = users.user_id " +
                "LEFT JOIN bid ON auction.auction_id = bid.auction_id " +
                "GROUP BY auction.auction_id";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            auctions.add(mapRowToAuctionListDto(results));
        }

        return auctions;
    }

    @Override
    public Auction getAuctionById(int id) {
        Auction auction = new Auction();
        List<Bid> bids = new ArrayList<>();
        String sql = "SELECT bid_id, auction_id, bid.user_id, users.username, amount " +
                "FROM bid " +
                "JOIN users ON bid.user_id = users.user_id " +
                "WHERE auction_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        while (results.next()) {
            bids.add(mapRowToBid(results));
        }
        sql = "SELECT auction.auction_id, owner_id, ou.username AS owner_username, title, description, starting_price, " +
                "bid.bid_id, bid.user_id, bu.username AS bid_username, bid.amount, " +
                "start_date, end_date, image_path " +
                "FROM auction " +
                "JOIN users AS ou ON owner_id = user_id " +
                "LEFT JOIN bid ON winning_bid_id = bid.bid_id " +
                "LEFT JOIN users AS bu ON bid.user_id = bu.user_id " +
                "WHERE auction.auction_id = ?";
        results = jdbcTemplate.queryForRowSet(sql, id);
        if (results.next()) {
            auction = mapRowToAuction(results);
        }
        auction.setBids(bids);
        return auction;
    }

    @Override
    public Auction updateAuction(UpdateAuctionDto dto) {
        String sql = "UPDATE auction " +
                "SET title = ?, description = ?, starting_price = ?, end_date = to_timestamp(?, 'YYYY-MM-DD HH24:MI:SS'), image_path = ? " +
                "WHERE auction_id = ?";
        jdbcTemplate.update(sql, dto.getTitle(), dto.getDescription(), dto.getStartingPrice(),
                dateFormat.format(dto.getEndDate()), dto.getImagePath(), dto.getId());
        return getAuctionById(dto.getId());
    }


    @Override
    public void deleteAuctionById(int id){
        // coding for deleting the individual auction
        String sql = "DELETE FROM auction " +
                     "WHERE auction_id = ?";
        jdbcTemplate.update(sql,id);


        //coding for deleting the bids associated with that auction.
        sql = "DELETE FROM bid " +
                "WHERE auction_id = ?";
        jdbcTemplate.update(sql,id);
    }



    private ListAuctionDto mapRowToAuctionListDto(SqlRowSet rs) {
        ListAuctionDto listAuctionDto = new ListAuctionDto();
        listAuctionDto.setId(rs.getInt("auction_id"));
        listAuctionDto.setTitle(rs.getString("title"));
        listAuctionDto.setOwnerName(rs.getString("username"));
        listAuctionDto.setImagePath(rs.getString("image_path"));
        listAuctionDto.setStartingPrice(rs.getBigDecimal("starting_price"));
        listAuctionDto.setHighestBid(rs.getBigDecimal("highest_bid"));
        listAuctionDto.setEndDate(rs.getTimestamp("end_date"));
        return listAuctionDto;
    }

    private Bid mapRowToBid(SqlRowSet rs) {
        Bid bid = new Bid();
        bid.setId(rs.getInt("bid_id"));
        bid.setAuctionId(rs.getInt("auction_id"));
        bid.setUserId(rs.getInt("user_id"));
        bid.setUsername(rs.getString("username"));
        bid.setBidAmount(rs.getBigDecimal("amount"));
        return bid;
    }

    private Auction mapRowToAuction(SqlRowSet rs) {
        Auction auction = new Auction();
        User owner = new User();
        Bid winningBid = new Bid();
        auction.setId(rs.getInt("auction_id"));
        owner.setId(rs.getInt("owner_id"));
        owner.setUsername(rs.getString("owner_username"));
        auction.setOwner(owner);
        auction.setTitle(rs.getString("title"));
        auction.setDescription(rs.getString("description"));
        auction.setStartingPrice(rs.getBigDecimal("starting_price"));
        if (rs.getString("bid_username") == null) {
            winningBid = null;
        }
        else {
            winningBid.setId(rs.getInt("bid_id"));
            winningBid.setUserId(rs.getInt("user_id"));
            winningBid.setUsername(rs.getString("bid_username"));
            winningBid.setBidAmount(rs.getBigDecimal("amount"));
        }
        auction.setWinningBid(winningBid);
        auction.setStartDate(rs.getTimestamp("start_date"));
        auction.setEndDate(rs.getTimestamp("end_date"));
        auction.setImagePath(rs.getString("image_path"));
        return auction;
    }

}
