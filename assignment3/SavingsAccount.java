package com.meritamerica.assignment3;

import java.text.ParseException;
import java.util.Date;

public class SavingsAccount extends BankAccount {

	//Instance Variables
	private double balance;
	private final double INTERESTRATE = 0.01;
	private long acountNumber;

	//Custom Constructor 1
	public SavingsAccount(double openBalance) {
		this.balance = openBalance;
		this.acountNumber = MeritBank.getNextAccountNumber();
	}
	
	//Custom Constructor 2
	public SavingsAccount(long accountNumber, double balance, double interestRate, Date openedOn) {
		super(accountNumber, balance, interestRate, openedOn);
	}

	//Mehtod to get balance, interestrate, and account number
	public double getBalance() {return this.balance;}
	public double getInterestRate() {return this.INTERESTRATE;}
	public long getAccountNumber() {return acountNumber;}

	//Method to withdraw from savings account
	public boolean withdraw(double amount) {
		if (amount <= balance && amount > 0) {
			this.balance -= amount;
			System.out.println("You withdrew: $" + amount);
			System.out.println("Your remaining balance is: $" + balance);
			if (amount <= balance && amount > 0) {
				this.balance = balance - amount;
				System.out.println("Withdrawn amount: $" + amount);
				System.out.println("Remaining balance: $" + balance);
				return true;
			}
			System.out.println("Insufficient Funds.");
			{
				return false;
			}
		}
		return false;
	}

	//Method to deposit into savings account
	public boolean deposit(double amount) {
		if (amount > 0) {
			this.balance += amount;
			if (amount > 0) {
				this.balance = balance + amount;
				System.out.println("Deposited amount: $" + amount);
				System.out.println("Total balance: $" + balance);
				return true;
			}
			System.out.println(amount + " - Invalid amount.");
			{
				return false;
			}
		}
		return false;
	}

	//Method to get future value of savings account
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

	@Override
	public String toString() {
		return "Savings Account Balance: $" + getBalance() + "\n" + "Savings Account Interest Rate: " + INTERESTRATE
				+ "%" + "\n" + "Savings Account Balance in 3 years: $" + String.format("%.2f", futureValue(3));

	}

	static SavingsAccount readFromString(String accountData) throws ParseException {
		String[] chunks = accountData.split(",");
		
		return  new SavingsAccount(
					Long.parseLong(chunks[0]),
					Double.parseDouble(chunks[1]),
					Double.parseDouble(chunks[2]),
					sdf.parse(chunks[3]));
						
	}
}

