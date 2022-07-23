package com.accounts.practice.reponse;

import java.util.List;

import com.accounts.practice.entity.Account;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class CustomerDetail {

	private Account account;
	private List<Cards> cards;
	private List<Loans> loans;
}
