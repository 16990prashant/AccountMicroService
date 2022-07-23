package com.accounts.practice.feignclients;

import java.util.List;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.accounts.practice.entity.Customer;
import com.accounts.practice.reponse.Cards;


@FeignClient("cards")
@LoadBalancerClient("cards")
public interface CardsFeignClient {

	@PostMapping("/myCards")	
	public List<Cards> getCardDetails(@RequestBody Customer theCustomer);
}
