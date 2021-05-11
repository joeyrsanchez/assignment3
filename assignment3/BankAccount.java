package com.meritamerica.assignment3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BankAccount {
	
	static SimpleDateFormat sdf = new SimpleDateFormat("MM/DD/YYYY");

	// Instance Variables
	double balance;
	double interestRate;
	Date accountOpenedOn;
	long accountNumber;

	// Default Constructor
	public BankAccount() {
	}

	// Custom Constructor 1
	public BankAccount(double balance, double interestRate) {
		this.balance = balance;
		this.interestRate = interestRate;

	}

	// Custom Constructor 2
	public BankAccount(double balance, double interestRate, Date accountOpenedOn) {
		this.balance = balance;
		this.interestRate = interestRate;
		this.accountOpenedOn = accountOpenedOn;
	}

	// Custom Constructor 3
	public BankAccount(long accountNumber, double balance, double interestRate, Date accountOpenedOn) {
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.interestRate = interestRate;
		this.accountOpenedOn = accountOpenedOn;
	}

	//Method to get balance, interestrate, accountnumber, and accountopenedon
	public double getBalance() {return balance;}
	public double getInterestRate() {return interestRate;}
	public long getAccountNumber() {return accountNumber;}
	public Date getAccountOpenedOn() {return accountOpenedOn;}

	static BankAccount readFromString(String accountDataString) throws ParseException {
		// 1,1000,0.0001,01/01/2020
		long acn;
		double bal;
		double intRate;
		Date date;

		try {
			// String s = "1,1000,0.001,01/01/2020";
			String[] data = accountDataString.split(",");
			acn = Long.parseLong(data[0]);
			bal = Double.parseDouble(data[1]);
			intRate = Double.parseDouble(data[2]);
			date = sdf.parse(data[3]);
			System.out.println(acn + " " + bal + " " + intRate + " " + date);
			if (data[2].equals("") || data[2] == null || data.length != 4) {
				throw new ParseException("Parse Exception", 0);
			}
		} catch (Exception e) {
			throw new java.text.ParseException("Parse Exception", 0);
		}
		
		return new BankAccount(acn, bal, intRate, date);
	}

	public static void main(String[] args) throws ParseException {
		try {
			BankAccount ba = readFromString("1,1000,0.0001,01/01/2020");
			System.out.println(ba);
		} catch (NumberFormatException nfe) {
			System.out.println("Number Format");
		} catch (java.text.ParseException pe) {
			System.out.println("Parse Exception caught in main");
		}
	}

	public String writeToString() {
		return "" + accountNumber + ","+ balance + "," + interestRate + "," 
				 + accountOpenedOn;
	}

	public boolean withdraw(double amount) {
		return true;
	}

	public boolean deposit(double amount) {
		System.out.println("Deposit");
		balance += amount;
		return true;
	}

	public double futureValue(int years) {
		return 0.0;
	}
}
