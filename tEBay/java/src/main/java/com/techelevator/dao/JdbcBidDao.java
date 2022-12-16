package com.techelevator.dao;

import com.techelevator.model.Bid;
import com.techelevator.model.CreateBidDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcBidDao implements BidDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Bid createBid(CreateBidDto dto) {
        String sql = "INSERT INTO bid (auction_id, user_id, amount) VALUES (?,?,?) RETURNING bid_id";
        int newId = jdbcTemplate.queryForObject(sql, int.class, dto.getAuctionId(), dto.getUserId(), dto.getBidAmount());
        return getBidById(newId);
    }

    @Override
    public Bid getBidById(int id) {
        Bid bid = new Bid();
        String sql = "SELECT bid_id, auction_id, bid.user_id, users.username, amount " +
                "FROM bid " +
                "JOIN users ON bid.user_id = users.user_id " +
                "WHERE bid_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        if (results.next()) {
            bid = mapRowToBid(results);
        }

        return bid;
    }

    @Override
    public BigDecimal getHighestBidAmountByAuctionId(int id) {
        String sql = "SELECT amount FROM bid " +
                "WHERE auction_id = ? " +
                "ORDER BY amount DESC " +
                "LIMIT 1";

        try {
            return jdbcTemplate.queryForObject(sql, BigDecimal.class, id);
        }
        catch (Exception e) {
            sql = "SELECT starting_price " +
                    "FROM auction " +
                    "WHERE auction_id = ?";
            return jdbcTemplate.queryForObject(sql, BigDecimal.class, id);
        }

    }

    @Override
    public List<Bid> getBidsByAuctionId(int id) {
        List<Bid> bids = new ArrayList<>();
        String sql = "SELECT bid_id, auction_id, bid.user_id, users.username, amount " +
                "FROM bid " +
                "JOIN users ON bid.user_id = users.user_id " +
                "WHERE auction_id = ? " +
                "ORDER BY amount DESC";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        while (results.next()) {
            bids.add(mapRowToBid(results));
        }
        return bids;
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

}
