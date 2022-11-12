package com.techelevator.tenmo;

import com.techelevator.tenmo.model.*;
import com.techelevator.tenmo.services.AccountService;
import com.techelevator.tenmo.services.AuthenticationService;
import com.techelevator.tenmo.services.ConsoleService;
import com.techelevator.tenmo.services.TransferService;
import io.cucumber.java.bs.A;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static java.math.BigDecimal.ZERO;

public class App {

    private static final String API_BASE_URL = "http://localhost:8080/";

    private final ConsoleService consoleService = new ConsoleService();
    private final AuthenticationService authenticationService = new AuthenticationService(API_BASE_URL);
    private final AccountService accountService = new AccountService();
    private final TransferService transferService = new TransferService();
    public Scanner inputScanner = new Scanner(System.in);

    private AuthenticatedUser currentUser;

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    private void run() {
        consoleService.printGreeting();
        loginMenu();
        if (currentUser != null) {
            mainMenu();
        }
    }
    private void loginMenu() {
        int menuSelection = -1;
        while (menuSelection != 0 && currentUser == null) {
            consoleService.printLoginMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                handleRegister();
            } else if (menuSelection == 2) {
                handleLogin();
            } else if (menuSelection != 0) {
                System.out.println("Invalid Selection");
                consoleService.pause();
            }
        }
    }

    private void handleRegister() {
        System.out.println("Please register a new user account");
        UserCredentials credentials = consoleService.promptForCredentials();
        if (authenticationService.register(credentials)) {
            System.out.println("Registration successful. You can now login.");
        } else {
            consoleService.printErrorMessage();
        }
    }

    private void handleLogin() {
        UserCredentials credentials = consoleService.promptForCredentials();
        currentUser = authenticationService.login(credentials);
        String token = currentUser.getToken();
        accountService.setAuthToken(token);
        transferService.setAuthToken(token);
        if (currentUser == null) {
            consoleService.printErrorMessage();
        }
    }

    private void mainMenu() {
        int menuSelection = -1;
        while (menuSelection != 0) {
            consoleService.printMainMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                viewCurrentBalance();
            } else if (menuSelection == 2) {
                viewTransferHistory();
            } else if (menuSelection == 3) {
                viewPendingRequests();
            } else if (menuSelection == 4) {
                sendBucks();
            } else if (menuSelection == 5) {
                requestBucks();
            } else if (menuSelection == 0) {
                continue;
            } else {
                System.out.println("Invalid Selection");
            }
            consoleService.pause();
        }
    }

	private void viewCurrentBalance() {
		// TODO Auto-generated method stub

       int userId = currentUser.getUser().getId();
       int accountId = accountService.getIdByUsername(currentUser.getUser().getUsername());
       BigDecimal balance = accountService.getBalance(accountId);
        System.out.println(balance.toString());;

		
	}

	private void viewTransferHistory() {
        // TODO Auto-generated method stub
        System.out.println("Transfer History: ");
        String loggedInAccountName = currentUser.getUser().getUsername();
        int loggedInAccountId = accountService.getIdByUsername(loggedInAccountName);
        int loggedInUserId = currentUser.getUser().getId();

        Transfer[] allTransfers = transferService.transferList();

        List<Transfer> userTransferHistory = new ArrayList<>();


        for (int i = 0; i < allTransfers.length; i++) {
            if (allTransfers[i].getAccountFrom() == loggedInAccountId || allTransfers[i].getAccountTo() == loggedInAccountId) {
                userTransferHistory.add(allTransfers[i]);
            }

        }
        System.out.println("----------------------------------------------------------");
        System.out.println("---------------------YOUR TRANSFERS-----------------------");
        System.out.println("----------------------------------------------------------");
        System.out.println("Transfer id:" + "      " + "User Name:    Transfer Amount:");
        for (Transfer transfer : userTransferHistory) {
            System.out.println(transfer.getTransferId() + "                 " + accountService.getUser(loggedInUserId).getUsername() + "            $" + transfer.getAmount());
        }
        System.out.println("Please enter Transfer Id for more details: ");
        int transferId = parseInt(inputScanner.nextLine());

        for (Transfer usertransfer : userTransferHistory) {

            if (usertransfer.getTransferId() == transferId) {

//                int userId = usertransfer.getUserId();
//                int receiverId = usertransfer.getAccountTo();
                String transferStatus = accountService.getTransferStatus(usertransfer.getTransferId());
                String transferType = accountService.getTransferType(usertransfer.getTransferId());


                String senderName = accountService.getUserNameByAccountId(usertransfer.getAccountFrom());
                String receiverName = accountService.getUserNameByAccountId(usertransfer.getAccountTo());



                System.out.println("----------------------------------------");
                System.out.println("------------TRANSFER DETAILS:-----------");
                System.out.println("----------------------------------------");
                System.out.println("ID: " + transferId);
                System.out.println("FROM: " + senderName);
                System.out.println("TO: " + receiverName);
                System.out.println("TYPE: " + transferType);
                System.out.println("STATUS: " + transferStatus);
                System.out.println(("AMOUNT: " + usertransfer.getAmount()));

            }


        }
    }

	private void viewPendingRequests() {
		// TODO Auto-generated method stub
		
	}

	private void sendBucks() {
		// TODO Auto-generated method stub

        User[] activeUsers = accountService.listUsers();
        for (int i = 0; i < activeUsers.length; i++){

            System.out.println((i + 1) + ". " + activeUsers[i].getUsername());

        }


        System.out.println("Please type the full username of the user you would like to transfer to.");
        String receiverName = inputScanner.nextLine().trim();
        int receiverAccountId = accountService.getIdByUsername(receiverName);



        System.out.println("Please enter the amount you would like to transfer.");
        BigDecimal transferAmount = inputScanner.nextBigDecimal();
        inputScanner.nextLine();
        int senderAccountId = accountService.getIdByUsername(currentUser.getUser().getUsername());
        BigDecimal senderBalance = accountService.getBalance(senderAccountId);
        BigDecimal updatedSenderBalance = senderBalance.subtract(transferAmount);
        int senderUserId = accountService.findIdByUsername(currentUser.getUser().getUsername());
        int receiverUserId = accountService.findIdByUsername(receiverName);


        Transfer newTransfer = new Transfer(senderAccountId, receiverAccountId, transferAmount);

        Account receiverAccount = new Account(receiverAccountId, receiverUserId, accountService.getBalance(receiverAccountId).add(transferAmount));
        receiverAccount.setUserId(receiverUserId);
        receiverAccount.setBalance(accountService.getBalance(receiverAccountId).add(transferAmount));
        Account senderAccount = new Account(senderAccountId, senderUserId, updatedSenderBalance);
        senderAccount.setBalance(updatedSenderBalance);
        senderAccount.setUserId(senderUserId);





        if (senderAccountId == receiverAccountId || transferAmount.compareTo(ZERO) <= 0 || transferAmount.compareTo(senderBalance) > 0){
            newTransfer.setTransferStatusId(3);
        }
        else {
            newTransfer.setTransferStatusId(2);
        }
        newTransfer.setTransferTypeId(2);
        if (newTransfer.getTransferStatusId() == 3){
            transferService.createTransfer(newTransfer);
            System.out.println("Transfer is rejected.");
        }
        else {

            transferService.createTransfer(newTransfer);
                accountService.updateAccount(receiverAccount);
                accountService.updateAccount(senderAccount);
        }


	}

	private void requestBucks() {
		// TODO Auto-generated method stub
        System.out.println("Undergoing maintenance. Have a nice day.");
	}

}
