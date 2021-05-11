package com.meritamerica.assignment3;

import java.text.ParseException;

public class CheckingAccount extends BankAccount {
	
	//Instance variables
	private double balance;
	private final double INTERESTRATE = 0.0001;
	private final double INTEREST_RATE = 0.0001;
	private long accountNumber;
	
	
	public static CheckingAccount readFromString(String accountData) throws ParseException {
		return(CheckingAccount)BankAccount.readFromString(accountData);
	}

	//Custom Constructor
	public CheckingAccount(double openBalance) {
		this.balance = openBalance;
		this.accountNumber = MeritBank.getNextAccountNumber();
	}
	//Method to get balance and interestrate
	public double getBalance() {return this.balance;}
	public double getInterestRate() {return INTERESTRATE;}
	
	//Method to withdraw from checking account
	public boolean withdraw(double amount) {
		if(amount <= balance && amount>0) {
			this.balance -= amount;
			System.out.println("You withdrew: $" + amount);
			System.out.println("Your remaining balance is: $" + balance);
		if(amount <= balance && amount > 0) {
			this.balance = balance - amount;
			System.out.println("Withdrawn amount: " + amount);
			System.out.println("Remaining balance: " + balance);
			return true;
		}
		System.out.println("Insufficient Funds.");
		return false;
		}
		return false;
		
	}
	
	//Method to deposit into checking account
	public boolean deposit(double amount) {
		if(amount > 0) {
			this.balance += amount;
		if (amount > 0) {
			this.balance = balance + amount;
			System.out.println("Deposited amount: " + amount);
			System.out.println("Total balance: " + balance);
			return true;
		}
		System.out.println(amount + " - Invalid amount.");
		return false;
	}
		return false;
	}
	
	//Method to calculate future value of checking account
	public double futureValue(int years) {
		double futureValue;
		if (years <= 0) {
			System.out.println("Invalid period of time");
			return -1;
		} else {
			futureValue = balance * Math.pow((1 + INTERESTRATE), years);
			return futureValue;
		}
	}
	public double getBalance1() {
		return balance;
	}
	public double getInterestRate1() {
		return INTEREST_RATE;
	}
	@Override
	public String toString() {
		return  "Checking Account Balance: $" + getBalance() + "\n" +
				"Checking Account Interest Rate: " + String.format( "%.4f", INTERESTRATE)+ "%" + "\n" +
				"Checking Account Balance in 3 years: $" + String.format( "%.2f", futureValue(3));	
	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public String toString1() {
		return "Checking Account Balance: $" + getBalance() + "\n" + "Checking Account Interest Rate: " + INTEREST_RATE
				+ "\n" + "Checking Account Balance in 3 years: $" + futureValue(3);
	}
}

