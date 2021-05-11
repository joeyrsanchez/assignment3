package com.meritamerica.assignment3;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;

public class AccountHolder implements Comparable<AccountHolder> {

	private String firstName;
	private String middleName;
	private String lastName;
	private String ssn;
	CheckingAccount[] checkingAccounts = new CheckingAccount[0];
	SavingsAccount[] savingsAccounts = new SavingsAccount[0];
	CDAccount[] CDAccounts = new CDAccount[0];
	private static final double CHECKINGFEE = 250000;

	public AccountHolder() {}
	// Constructors
	public AccountHolder(String firstName, String middleName, String lastName, String ssn) {

		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.ssn = ssn;
	}
	
	private AccountHolder(String firstName, String middleName, String lastName, String ssn, 
			CheckingAccount[] checkingAccounts,
			SavingsAccount[] savingsAccounts,
			CDAccount [] cdAccounts) {

		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.ssn = ssn;
		this.checkingAccounts = checkingAccounts;
		this.savingsAccounts = savingsAccounts;
		this.CDAccounts = cdAccounts;
	}


	// Account GETTERS and SETTERS

	// First name setter & getter
	protected void setFirstName(String firstName) {this.firstName = firstName;}
	protected String getFirstName() {return firstName;}

	// Middle name setter & getter
	protected void setMiddleName(String middleName) {this.middleName = middleName;}
	protected String getMiddleName() {return middleName;}

	// Last name setter & getter
	protected void setLastName(String lastName) {this.lastName = lastName;}

	protected String getLastName() {return lastName;}

	// SSN setter & getter
	protected void setSSN(String ssn) {this.ssn = ssn;}
	protected String getSSN() {return ssn;}

	// Method to Add CheckingAccount
	public CheckingAccount addCheckingAccount(double openingBalance) {
		if (getCombinedBalance() - getCDBalance() + openingBalance <= CHECKINGFEE) {
			System.out.println("pass checking fee: ");
			CheckingAccount cac = new CheckingAccount(openingBalance);
			if (cac != null) {
				System.out.println("Account created: ");
				return addCheckingAccount(cac);
			}
		}
		return null;
	}

	//Method to add a Checking Account
	public CheckingAccount addCheckingAccount(CheckingAccount checkingAccount) {
		if (getCombinedBalance() - getCDBalance() + checkingAccount.getBalance() <= CHECKINGFEE) {
			System.out.println("Account fee acepted: ");
			for (int i = 0; i < checkingAccounts.length; i++) {
				if (checkingAccounts[i] == null) {
					checkingAccounts[i] = checkingAccount;
					System.out.println(checkingAccounts.length + " add checking account");
					return checkingAccounts[i];
				}
			}
		}
		return null;
	}
	//Method to return list of checking accounts
	public CheckingAccount[] getCheckingAccounts() {return checkingAccounts;}
	
	//Method to return the contents of the checking accounts
	public int getNumberOfCheckingAccount() {return checkingAccounts.length;}

	// Method to get Checking Account Balance
	public double getCheckingBalance() {
		if (checkingAccounts != null) {
			double checkingBalance = 0.0;
			for (int i = 0; i < checkingAccounts.length; i++) {
				if (checkingAccounts[i] != null) {
					checkingBalance += checkingAccounts[i].getBalance();
				}
			}
			return checkingBalance;
		} else
			return 0;
	}

	// Method to Add Savings Account
	public SavingsAccount addSavingsAccount(double openingBalance) {
		if (getCombinedBalance() - getCDBalance() + openingBalance <= CHECKINGFEE) {
			return addSavingsAccount(new SavingsAccount(openingBalance));
		}
		return null;
	}

	public SavingsAccount addSavingsAccount(SavingsAccount openingBalance) {
		if (getCombinedBalance() - getCDBalance() + openingBalance.getBalance() <= CHECKINGFEE) {
			for (int i = 0; i < savingsAccounts.length; i++) {
				if (savingsAccounts[i] == null) {
					savingsAccounts[i] = openingBalance;
					break;
				}
			}
		}
		return null;
	}

	//Method to return list of savings accounts
	public SavingsAccount[] getSavingsAccounts() {return savingsAccounts;}
	
	//Method to return contents of savings accounts
	public int getNumberOfSavingsAccounts() {return savingsAccounts.length;}

	// Method to get Savings Account Balance
	public double getSavingsBalance() {
		if (savingsAccounts != null) {
			double savingsBalance = 0.0;
			for (int i = 0; i < savingsAccounts.length; i++) {
				if (savingsAccounts[i] != null) {
					savingsBalance += savingsAccounts[i].getBalance();
				}
			}
			return savingsBalance;
		}
		return 0;
	}

	// Method to Add CD Account
	public CDAccount addCDAccount(CDOffering offering, double openingBalance) {
		if (MeritBank.getCDOfferings() != null) {
			return addCDAccount(new CDAccount(offering, openingBalance));
		}
		return null;
	}

	public CDAccount addCDAccount(CDAccount cdAccount) {
		for (int i = 0; i < CDAccounts.length; i++) {
			if (CDAccounts[i] == null) {
				CDAccounts[i] = cdAccount;
				return cdAccount;
			}
		}

		return null;
	}
	
	//Method to return list of CD accounts
	public CDAccount[] getCDAccounts() {return CDAccounts;}
	
	//Method to return CD accounts contents
	public int getNumberOfCDAccounts() {return CDAccounts.length;}

	// Method to get CD Account Balance
	public double getCDBalance() {
		if (CDAccounts != null) {
			double CDBalance = 0.0;
			for (int i = 0; i < CDAccounts.length; i++) {
				if (CDAccounts[i] != null) {
					CDBalance += CDAccounts[i].getBalance();
				}
			}
			return CDBalance;
		}
		return 0;
	}

	// Method to get Combined Account Balance
	public double getCombinedBalance() {
		double res = getCheckingBalance() + getSavingsBalance() + getCDBalance();
		System.out.println(res);
		return res;
	}

	public static AccountHolder readFromString(String accountHolderData) throws Exception {
		/*
		 * Doe,,John,1234567890 1 1,1000,0.0001,01/01/2020 2 2,10000,0.01,01/02/2020
		 * 3,150000,0.01,01/02/2020 0
		 */

		Scanner scan = new Scanner(accountHolderData);
		String[] chunks = scan.nextLine().split(",");
		String firstName = chunks[0];
		String middleName = chunks[1];
		String lastName = chunks[2];
		String ssn = chunks[3];

		int numCheckingAccounts = Integer.parseInt(scan.nextLine());
		CheckingAccount[] tempCAs = new CheckingAccount[numCheckingAccounts];
		for (int i = 0; i < numCheckingAccounts; i++) {
			tempCAs[i] = CheckingAccount.readFromString(scan.nextLine());	
		}
		
		int numSavingsAccounts = Integer.parseInt(scan.nextLine());
		SavingsAccount[] tempSAs = new SavingsAccount[numSavingsAccounts];
		for (int i = 0; i < numSavingsAccounts; i++) {
			tempSAs[i] = SavingsAccount.readFromString(scan.nextLine());	
		}
		
		int numCDAccounts = Integer.parseInt(scan.nextLine());
		CDAccount[] tempCDAs = new CDAccount[numCDAccounts];
		for (int i = 0; i < numCDAccounts; i++) {
			tempCDAs[i] = CDAccount.readFromString(scan.nextLine());	
		}
		return new AccountHolder(firstName, middleName, lastName, ssn, tempCAs, tempSAs, tempCDAs);
				
	}
	
	private void writeAccountsToString(BankAccount[] accounts, PrintWriter pw) {
		pw.println(accounts.length);
		
		for(BankAccount ba : accounts) {
			pw.println(ba.writeToString());
		}
	}
	// String write ToSting()
	@Override
	public int compareTo(AccountHolder that) {
		double thisBalance = getCombinedBalance ();
		double thatBalance = that.getCombinedBalance();
		if (thisBalance > thatBalance)
			return 1;
		else if (thisBalance > thatBalance)
			return -1;
		else
			return 0;
	}
	String writeToString() {
		StringWriter sw = new StringWriter ();
		
		try (PrintWriter pw = new PrintWriter (sw)) {
			pw.format("%s,%s,%s,%s", firstName, middleName, lastName, ssn)
			.println();
			
			writeAccountsToString(checkingAccounts, pw);
			writeAccountsToString(savingsAccounts,pw);
			writeAccountsToString(CDAccounts, pw);
		}
		return sw.toString();
	}
}

