package com.meritamerica.assignment3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class MeritBank {
	
	//Instance Variables
	public static AccountHolder[] myAccountHolder = new AccountHolder[2];
	public static CDOffering[] myCDOffering = new CDOffering[3];
	public static CDAccount myCDAccount[] = new CDAccount[2];
	private static long nextAccountNumber;
	
	
	//Default Constructor
	//public MeritBank (){}
	

	
	
	//Method for Adding an Account Holder
	public static void addAccountHolder(AccountHolder accountHolder) {
		for(int i = 0; i < myAccountHolder.length - 1; i++) {
			if(myAccountHolder[i] == null) {
				myAccountHolder[i] = accountHolder;
				break;
			}
			if(myAccountHolder.length > 10) {
				System.out.println("Sorry you cannot create more accounts");
			}
		}
	}
	
	//Method for Getting an Account Holder
	public static AccountHolder[] getAccountHolders() {
		System.out.println("account Holder" + myAccountHolder[0]);
		return myAccountHolder;
	}
	
	//Method for Getting an CD Offering
	public static CDOffering[] getCDOfferings() {
		return myCDOffering;
	}
	
	//Method for Getting an Best CD Offering
	public static CDOffering getBestCDOffering(double depositAmount) {
		double best = 0.0;
		CDOffering CDO = null;
		if (myCDOffering == null) {
			return null;
		}
		for (int i = 0; i < myCDOffering.length; i++) {
			if (futureValue(depositAmount, CDO.getInterestRate(), CDO.getTerm()) > best) {
				CDO = myCDOffering[i];
				best = futureValue(depositAmount, CDO.getInterestRate(), CDO.getTerm());
			}
		}
		return CDO;
	}
	//Method for Getting an Second Best CD Offering
	public static CDOffering getSecondBestCDOffering(double depositAmount) {
		double secondBest = 00;
		CDOffering CDO = null;
		CDOffering CDB = null;
		if (myCDOffering == null) {
			return null;
		}
		for (int i = 0; i < myCDOffering.length; i++) {
			if (futureValue(depositAmount, CDO.getInterestRate(), CDO.getTerm()) > secondBest) {
				CDB = CDO;
				CDO = myCDOffering[i];
				secondBest = futureValue(depositAmount, CDO.getInterestRate(), CDO.getTerm());
			}
		}
		return CDB;
	}

	public static void clearCDOfferings() {
		myCDOffering = null;
	}

	public static void setCDOfferings(CDOffering[] offerings) {
	}
	
	//Method for Getting Next Account Number
	public static long getNextAccountNumber() {
		return nextAccountNumber;
	}
	
	public static double totalBalances() {
		double total = 0;
		for (int i = 0; i < myAccountHolder.length; i++) {
			total += myAccountHolder[i].getCombinedBalance();
		}
		return total;
	}

	static double futureValue(double presentValue, double interestRate, int term) {
		double futureValue = presentValue * Math.pow(1 + interestRate, term);
		return futureValue;
	}
	
	
	public static boolean readFromFile(String fileName) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			
			String line = br.readLine();
			long nextAccountNumber = Long.parseLong(line);
			MeritBank.setNextAccountNumber(nextAccountNumber);
			
			int numCDOfferings = Integer.parseInt(br.readLine());
			CDOffering[] tempCDOs = new CDOffering[numCDOfferings];
			for (int i = 0; i < numCDOfferings; i++) {
				tempCDOs[i] = CDOffering.readFromString(br.readLine());
			}
			setCDOfferings (tempCDOs);
			
			int numAccountHolders = Integer.parseInt(br.readLine());
			for (int i = 0; i < numAccountHolders; i++) {
				StringWriter sw = new StringWriter();
				
				try (PrintWriter pw = new PrintWriter (sw)) {
					pw.println(br.readLine());
					int numCAs = Integer.parseInt(br.readLine());
					pw.println(numCAs);
					for (int j = 0; j < numCAs; j++) {
						pw.println(br.readLine());
					}
					
					int numSAs = Integer.parseInt(br.readLine());
					pw.println(numSAs);
					for (int j = 0; j < numSAs; j++) {
						pw.println(br.readLine());
					}
					
					int numCDAs = Integer.parseInt(br.readLine());
					pw.println(numCDAs);
					for (int j = 0; j < numCDAs; j++) {
						pw.println(br.readLine());
					}
				}
				
				addAccountHolder(AccountHolder.readFromString(sw.toString()));
			}
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	private static void setNextAccountNumber(long nextAccountNumber) {
		MeritBank.nextAccountNumber = nextAccountNumber;
		
	}

	public static boolean writeToFile(String fileName) {
		try {
		FileWriter accRecords = new FileWriter (fileName);
		PrintWriter printRecords = new PrintWriter (accRecords);
		printRecords.println(getNextAccountNumber());
		
		printRecords.println(myCDOffering.length);
		for (int i =0; i < myCDOffering.length; i++) {
			CDOffering CDO = myCDOffering[i];
			printRecords.println(CDO.writeToString());}
			
		printRecords.println(myAccountHolder.length);
		for (int i =0; i < myAccountHolder.length; i++) {
			AccountHolder acchold = myAccountHolder[i];
			printRecords.println(acchold.writeToString());}
		
		return true;	
		}
		catch (IOException err) {
			err.printStackTrace();
			return false;
		}
	}
}

