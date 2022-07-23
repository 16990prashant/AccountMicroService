package com.accounts.practice.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.accounts.practice.entity.Account;


@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
	Optional<Account> findByCustomerId(Integer id);
}
