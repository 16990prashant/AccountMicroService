package com.accounts.practice.feignclients;

import java.util.List;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.accounts.practice.entity.Customer;
import com.accounts.practice.reponse.Loans;

@FeignClient("loans")
@LoadBalancerClient("loans")
public interface LoansFeignClient {
	
	@PostMapping("/myLoans")	
	public List<Loans> getLoanDetails(@RequestBody Customer theCustomer);

}
