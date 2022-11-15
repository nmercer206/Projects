package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.Math.floor;

public class Program {

	public static void main(String[] args) throws FileNotFoundException {
		SimpleDateFormat dateTime = new SimpleDateFormat("MM/dd/yyyy hh.mm.ss aa");
		String currentTime = dateTime.format(new Date()).toString();
		Map<String, Product> productMap = new HashMap<>();
		User user = new User();
		List<String> productCode = new ArrayList<>();
		String salesPath = "C:\\Users\\Student\\workspace\\java-minicapstonemodule1-team4\\saleslog.txt";
		File salesLog = new File(salesPath);

		String invPath = "C:\\Users\\Student\\workspace\\java-minicapstonemodule1-team4\\vendingmachine.csv";
		File invFile = new File(invPath);
		try (Scanner invScanner = new Scanner(invFile)) {
			while (invScanner.hasNextLine()) {
				String currentLine = invScanner.nextLine();
				String[] productParts = currentLine.split(",");
				String productLocation = productParts[0];
				String productName = productParts[1];
				double productPrice = Double.parseDouble(productParts[2]);
				String productType = productParts[3];
				productMap.put(productLocation, new Product(productName, productPrice, productType));
				productCode.add(productLocation);

			}
		}


		System.out.println("*************************************************************");
		System.out.println("*Very-Convenient-Vending-Service-Tron-O-Matic-1006-PLUS-9001*");
		System.out.println("*************************************************************");

		// menu

		Scanner userRead = new Scanner(System.in);



		        // expecting 1 - 3


									// First Menu


		try (FileWriter salesWriter = new FileWriter(salesLog, true)) {
			while (1 < 2){
				System.out.println();
				System.out.println();
				System.out.println("(1) Display Vending Machine Items"); // print inventory and return to this menu
				System.out.println("(2) Purchase");                     //
				System.out.println("(3) Exit");
				String userChoice = userRead.nextLine();
				if (userChoice.equals("1")) {
					//output inventory screen and return to menu
					for (String key : productCode){
						System.out.println(key +" "+ productMap.get(key).getName() + " $" + String.format("%.2f",productMap.get(key).getPrice()) + " (" + productMap.get(key).getStock()+ " in stock)");

					}
				}
				// PRODUCT MENU

				else if(userChoice.equals("2")) {
					while (true) {
						System.out.println("Current Balance: $" + String.format("%.2f",user.getBalance()));
						System.out.println();
						System.out.println();
						System.out.println("(1) Feed Money");
						System.out.println("(2) Select Product");
						System.out.println("(3) Finish Transaction");
						String purchaseChoice = userRead.nextLine();

						if (purchaseChoice.equals("1")){
								boolean incorrectInput;
								do {
									System.out.println("How much would you like to deposit? (Please enter a single whole number with no decimals) ");
									String sMoney = userRead.nextLine();
									try {
										double money = Integer.parseInt(sMoney);
										user.increaseBalance(money);
										System.out.println("Deposited: $" + String.format("%.2f", money));
										salesWriter.write(currentTime + " FEED MONEY: $" + String.format("%.2f", money) + " $" + String.format("%.2f", user.getBalance()) + System.getProperty("line.separator"));
										incorrectInput = false;
									}
									catch (NumberFormatException e) {
										System.out.println("Please enter whole dollar amounts only!");
										incorrectInput = true;
									}
								} while (incorrectInput);


						}
						else if (purchaseChoice.equals("2")) {
							while (true) {
								System.out.println();
								for (String key : productCode) {
									System.out.println(key + " " + productMap.get(key).getName() + " $" + String.format("%.2f",productMap.get(key).getPrice()) + " (" + productMap.get(key).getStock() + " in stock)");
								}
								System.out.println("Please enter a product code: ");
								String productChoice = userRead.nextLine();
								///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
								if (!productMap.containsKey(productChoice)) {
									System.out.println(productChoice + " is not a valid code.");
									break;
								}
								///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
								else if (productMap.get(productChoice).getStock() <= 0) {
									System.out.println(productChoice + "(" + productMap.get(productChoice).getName() + ") is out of stock.");
									break;
								}
								///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
								else if (user.getBalance() < productMap.get(productChoice).getPrice()) {
									System.out.println("You do not have enough money!    Balance: $" + String.format("%.2f",user.getBalance()) + "   Product Price: $" + String.format("%.2f",productMap.get(productChoice).getPrice()));
									break;
								}
								///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
								else {
									user.subtractBalance(productMap.get(productChoice).getPrice());
									productMap.get(productChoice).decreaseStock();
									System.out.println(productMap.get(productChoice).getName() + ", $" + String.format("%.2f",productMap.get(productChoice).getPrice()) + ", $" + String.format("%.2f",user.getBalance()));
									if (productMap.get(productChoice).getType().equalsIgnoreCase("chip")) {
										System.out.println("Crunch crunch, yum!");
									}
									else if(productMap.get(productChoice).getType().equalsIgnoreCase("candy")) {
										System.out.println("Munch munch, yum!");
									}
									else if(productMap.get(productChoice).getType().equalsIgnoreCase("drink")) {
										System.out.println("Slurp slurp, yum!");
									}
									else if(productMap.get(productChoice).getType().equalsIgnoreCase("gum")) {
										System.out.println("Chew chew, yum!");
									}
									salesWriter.write(currentTime + " " + productMap.get(productChoice).getName() + " " + productChoice + " " + String.format("%.2f", productMap.get(productChoice).getPrice()) + " $" + String.format("%.2f",user.getBalance()) + System.getProperty("line.separator"));
									break;
								}
								///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
							}
						}
						else if (purchaseChoice.equals("3")) {
							{
								double changeDN = user.getBalance() % .25;
								double changeQ = floor(user.getBalance() / .25);
								double changeD = 0;
								double changeN = 0;
//							double actChangeQ = floor(changeQ);


								if (changeDN > 0){
									changeD = (floor(changeDN * 10));
									changeN = (((changeDN * 10) - floor(changeDN * 10)) * 2);
								}
								System.out.println("You will receive: " + String.format("%.0f",changeQ)+ " quarters, " + String.format("%.0f",changeD) + " dimes, " + "and " + String.format("%.0f",changeN) + " nickel, yo.");
								salesWriter.write(currentTime + " GIVE CHANGE: $" + String.format("%.2f", user.getBalance()) + " $0.00" + System.getProperty("line.separator"));
								user.subtractBalance(user.getBalance());
								break;

							}
						}
					}

				}
				else if(userChoice.equals("3")) {
					System.out.println();
					System.out.println("Thank you! Have a nice day!");
					break;
				}
				else {
					System.out.println("Please enter a valid input.");
					System.out.println("(1) Display Vending Machine Items");
					System.out.println("(2) Purchase");
					System.out.println("(3) Exit");
					continue;
				}



			}
		}
		catch (IOException e) {

		}


			//user input #


	}


}



