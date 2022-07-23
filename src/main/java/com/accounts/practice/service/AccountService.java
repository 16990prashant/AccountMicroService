package com.accounts.practice.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accounts.practice.entity.Account;
import com.accounts.practice.entity.Customer;
import com.accounts.practice.repository.AccountRepository;

@Service
public class AccountService {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	private AccountRepository accountRepository;
	
	public Account get(Integer id) {
		
		Optional<Account> account = accountRepository.findByCustomerId(id);				
		if (account.isPresent()) {
			return account.get();
		} else {
			throw new RuntimeException("account not found with id : " + id);
		}

	}

}
