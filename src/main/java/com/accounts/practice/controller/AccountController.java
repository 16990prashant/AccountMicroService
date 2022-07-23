package com.accounts.practice.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.accounts.practice.config.AccountsConfig;
import com.accounts.practice.entity.Account;
import com.accounts.practice.entity.Customer;
import com.accounts.practice.feignclients.CardsFeignClient;
import com.accounts.practice.feignclients.LoansFeignClient;
import com.accounts.practice.reponse.Cards;
import com.accounts.practice.reponse.CustomerDetail;
import com.accounts.practice.reponse.Loans;
import com.accounts.practice.reponse.PropertiesBo;
import com.accounts.practice.service.AccountService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.Builder;

@RestController
@Builder
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AccountsConfig accountConfig;
	
	@Autowired
	private CardsFeignClient cardsFeignClient;
	
	@Autowired
	private LoansFeignClient loansFeignClient;
	
	@PostMapping("/myAccount")	
	public Account get(@RequestBody Customer theCustomer) {		
		return accountService.get(theCustomer.getCustomerId());
	}
	
	@GetMapping("/account/branches")
	public List<String> getActiveBranches() {
		return accountConfig.getActiveBranches();
	}

	@GetMapping("/account/properties")
	public PropertiesBo getAllConfigProperties() {
		PropertiesBo properties = new PropertiesBo();
		BeanUtils.copyProperties(accountConfig, properties);
		return properties;
	}
	
	@PostMapping("/myCustomerDetails")
	//@CircuitBreaker(name = "customerSupportBreaker",fallbackMethod = "myCustomerDetails_Fallback")
	@Retry(name = "customerSupportRetry")
	public CustomerDetail getCustomerDetails(@RequestBody Customer customer) {
		Account account = accountService.get(customer.getCustomerId());
		List<Cards> cardDetais = cardsFeignClient.getCardDetails(customer);
		List<Loans> loanDetails = loansFeignClient.getLoanDetails(customer);
		
		return CustomerDetail.builder()
				.account(account)
				.loans(loanDetails)
				.cards(cardDetais)
				.build();	
	}
	
	public CustomerDetail myCustomerDetails_Fallback(@RequestBody Customer customer, Throwable t) {
		Account account = accountService.get(customer.getCustomerId());
		List<Loans> loanDetails = loansFeignClient.getLoanDetails(customer);
		
		return CustomerDetail.builder()
				.account(account)
				.loans(loanDetails)
				.build();	
	}
}





