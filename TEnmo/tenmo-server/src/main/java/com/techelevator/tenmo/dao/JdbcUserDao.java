package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcUserDao implements UserDao {

    private static final BigDecimal STARTING_BALANCE = new BigDecimal("1000.00");
    private final JdbcTemplate jdbcTemplate;

    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int findIdByUsername(String username) {
        if (username == null) throw new IllegalArgumentException("Username cannot be null");

        int userId;
        try {
            userId = jdbcTemplate.queryForObject("SELECT user_id FROM tenmo_user WHERE username = ?", int.class, username);
        } catch (NullPointerException | EmptyResultDataAccessException e) {
            throw new UsernameNotFoundException("User " + username + " was not found.");
        }

        return userId;
    }

    //WROTE GET BALANCE
    @Override
    public BigDecimal getBalanceById (int accountId){
        String sql = "SELECT balance FROM account WHERE account_id = ?;";
        BigDecimal balance = jdbcTemplate.queryForObject(sql, BigDecimal.class, accountId);

        return balance;
    }

//    @Override
//    public void transferTo (int fromId, String transferName, BigDecimal transferAmount){
//        boolean approved = true;
//
//        BigDecimal fromBalance = getBalanceById(fromId);
//
//        String sql = "Select user_id FROM account WHERE user_name = ?;";
//        int toId = jdbcTemplate.queryForObject(sql, Integer.class, transferName);
//
//
//        if (fromId == toId || fromBalance.compareTo(transferAmount) < 0 || transferAmount.compareTo(BigDecimal.ZERO) <= 0 ){
//        approved = false;
//        }
//        if (approved == true) {
//            sql = "UPDATE account SET balance - ? WHERE user_id = ?;";
//            jdbcTemplate.update(sql, transferAmount, fromId);
//            sql = "UPDATE account SET balance + ? WHERE user_id = ?;";
//            jdbcTemplate.update(sql, transferAmount, toId);
//            System.out.println("Transaction Approved!");
//            System.out.println("Your updated balance is: " + (fromBalance.subtract(transferAmount)));
//        }
//        System.out.println("Transaction unsuccessful");
//    }

    @Override
    public User getUserById(int userId) {
        String sql = "SELECT user_id, username, password_hash FROM tenmo_user WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        if (results.next()) {
            return mapRowToUser(results);
        } else {
            return null;
        }
    }

    @Override
    public int getIdByUsername(String username) {
        String sql = "SELECT account_id FROM account " +
                "JOIN tenmo_user ON account.user_id = tenmo_user.user_id " +
                "WHERE username = ?;";
        int userId = jdbcTemplate.queryForObject(sql, Integer.class, username);
        return userId;
    }

    @Override
    public String getUserNameByAccountId(int accountId) {
        String sql = "SELECT tenmo_user.username FROM account " +
                "JOIN tenmo_user ON account.user_id = tenmo_user.user_id " +
                "WHERE account.account_id = ?;";
        String userName = jdbcTemplate.queryForObject(sql, String.class, accountId);
        return userName;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT user_id, username, password_hash FROM tenmo_user";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            User user = mapRowToUser(results);
            users.add(user);
        }

        return users;
    }

    @Override
    public User findByUsername(String username) {
        if (username == null) throw new IllegalArgumentException("Username cannot be null");

        String sql = "SELECT user_id, username, password_hash FROM tenmo_user WHERE username = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, username);
        if (rowSet.next()) {
            return mapRowToUser(rowSet);
        }
        throw new UsernameNotFoundException("User " + username + " was not found.");
    }

    @Override
    public User findByUserId(int userId) {

        String sql = "SELECT user_id, username FROM tenmo_user WHERE user_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);
        if (rowSet.next()) {
            return mapRowToUser(rowSet);
        }
        throw new UsernameNotFoundException("User " + userId + " was not found.");
    }



    @Override
    public boolean create(String username, String password) {

        // create user
        String sql = "INSERT INTO tenmo_user (username, password_hash) VALUES (?, ?) RETURNING user_id";
        String password_hash = new BCryptPasswordEncoder().encode(password);
        Integer newUserId;
        newUserId = jdbcTemplate.queryForObject(sql, Integer.class, username, password_hash);

        if (newUserId == null) return false;

        // create account
        sql = "INSERT INTO account (user_id, balance) values(?, ?)";
        try {
            jdbcTemplate.update(sql, newUserId, STARTING_BALANCE);
        } catch (DataAccessException e) {
            return false;
        }

        return true;
    }

    //TODO Create User Balance getter to Database

    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password_hash"));
        user.setActivated(true);
        user.setAuthorities("USER");
        return user;
    }
}
