package com.accounts.practice.reponse;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Loans {

	private int loanNumber;
	
	private int customerId;
	
	private Date startDt;
	
	private String loanType;
	
	private int totalLoan;
	
	private int amountPaid;
	
	private int outstandingAmount;
	
	private String createDt;
}
