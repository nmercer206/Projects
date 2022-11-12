package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import org.springframework.web.client.RestTemplate;
import com.techelevator.util.BasicLogger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.UserCredentials;

import java.math.BigDecimal;

public class AccountService {

    private static  final String API_BASE_URL = "http://localhost:8080/";
    private final RestTemplate restTemplate = new RestTemplate();


    private String authToken;
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }



    /**
     *  Getting account balance
     */


    public BigDecimal getBalance(int accountId) {
        BigDecimal balance = null;

        try {
            ResponseEntity <BigDecimal> response = restTemplate.exchange(API_BASE_URL + "account/" + accountId+ "/balance", HttpMethod.GET, makeAuthEntity(), BigDecimal.class);
            balance = response.getBody();
        } catch (RestClientResponseException |ResourceAccessException e){
            BasicLogger.log(e.getMessage());
        }
        return balance;
    }

    public String getUserNameByAccountId(int accountId) {
        String userName = "";
        try {
            userName = restTemplate.getForObject(API_BASE_URL + "account/username/" + accountId, String.class);
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return userName;
    }

    public User getUser(int userId) {
        User user = null;

        try {
            user = restTemplate.getForObject(API_BASE_URL + userId,User.class);
        } catch (RestClientResponseException |ResourceAccessException e){
            BasicLogger.log(e.getMessage());
        }
        return user;
    }


    public Account updateAccount(Account account){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authToken);
        HttpEntity<Account> entity = new HttpEntity<>(account, headers);

        try {
            restTemplate.put(API_BASE_URL + "account/" + account.getAccountId(), entity);
        } catch (RestClientResponseException |ResourceAccessException e){
            BasicLogger.log(e.getMessage());
        }

        return account;
    }
    public String getTransferStatus(int transferId) {
        String transferStatus = "";
        try {
            transferStatus = restTemplate.getForObject(API_BASE_URL + "transfer/transfer_status/" + transferId, String.class);
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return transferStatus;
    }

    public String getTransferType(int transferId) {
        String transferType = "";
        try {
            transferType = restTemplate.getForObject(API_BASE_URL + "transfer/transfer_type/" + transferId, String.class);
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return transferType;
    }



    public User[] listUsers(){
        User[] users = restTemplate.getForObject(API_BASE_URL + "account", User[].class);
        return users;

    }


    public void transferTo(String transferName, BigDecimal transferAmount){

        try {
            restTemplate.put(API_BASE_URL + "account/" + transferName + "/transfer", BigDecimal.class);
        }
        catch (RestClientResponseException |ResourceAccessException e){
            BasicLogger.log(e.getMessage());
        }

    }

    public int getIdByUsername(String username) {
        int accountId = 0;
        try {
            accountId = restTemplate.getForObject(API_BASE_URL + "account/" + username, Integer.class);
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return accountId;
    }

    public int findIdByUsername(String username) {
        int userId = 0;
        try {
            userId = restTemplate.getForObject(API_BASE_URL + "tenmo_user/" + username, Integer.class);
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return userId;
    }

    private HttpEntity<Void> makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(headers);
    }

}
