package com.meritamerica.assignment3;

public class CDOffering {
	
	//Instance Variables
	private int term;
	private double interestRate;
	private double balance;

	//Custom Constructor
	CDOffering(int term, double interestRate) {
		this.term = term;
		this.interestRate = interestRate;
	}

	//Methods to get Term and InterestRate
	public int getTerm() {return term;}
	public double getInterestRate() {return interestRate;}
	public double getBalance() {return balance;}
	

    static CDOffering readFromString(String cdOfferingDataString) {
    	String[] holding = cdOfferingDataString.split(",");
    	int term = Integer.parseInt(holding[0]);
    	double interestRate = Double.parseDouble(holding[1]);
    	return new CDOffering(term, interestRate);
    }

    public String writeToString() {
    	StringBuilder cdOfferingData = new StringBuilder();
    	cdOfferingData.append(term).append(",");
    	cdOfferingData.append(interestRate);
    	return cdOfferingData.toString(); 
    }
}
