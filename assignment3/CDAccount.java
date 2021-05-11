package com.meritamerica.assignment3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CDAccount extends BankAccount{

	//Instance Variables
	CDOffering offering;
	Date date;
	
	//Default Constructor
	public CDAccount() {}
	
	//Custom Constructor
	public CDAccount(CDOffering offering, double balance) {
		super(balance, offering.getInterestRate());
		this.date = new Date();
		this.offering = offering;
	}
	
	//Methods to get InterestRate, Term, and Balance
	public double getInterestRate() {return this.offering.getInterestRate();}
	public int getTerm() {return this.offering.getTerm();}
	public double getBalance() {return this.balance;}
	
	//Method to return future value
	public double futureValue() {
		return getBalance()*Math.pow(getInterestRate(), getTerm());
	}
	
	@Override
	//Override the deposit and withdraw methods to return false 
	public boolean withdraw(double amount) {return false;}
	public boolean deposit(double amount) {return false;}	
	
	//(CD Accounts cannot deposit new funds or withdraw funds until the term is reached)
	public static CDAccount readFromString(String accountData) throws ParseException {
	
		//divides or splits information between commas
		String[] chunks = accountData.split(",");
		long accountNumber = Long.parseLong(chunks[0]);
		double balance = Double.parseDouble(chunks[1]);
		double interestRate = Double.parseDouble(chunks[2]);
		Date date = sdf.parse(chunks[3]);
		int term = Integer.parseInt(chunks[4]);
		
		CDAccount cda = new CDAccount();
		cda.accountNumber = accountNumber;
		cda.balance = balance;
		cda.offering = new CDOffering(term, interestRate);
		cda.date = date;
		cda.interestRate = interestRate;
		
		return cda;
	}
	
	public String writeToString() {
		return "" + accountNumber + ","+ balance + "," + interestRate + "," 
				 + accountOpenedOn + "," + offering.getTerm(); 		
	}
}
