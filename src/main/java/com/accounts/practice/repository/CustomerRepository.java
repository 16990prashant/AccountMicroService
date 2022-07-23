package com.accounts.practice.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.accounts.practice.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
