package com.nashtech.rookies.repository.spring;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nashtech.rookies.model.Account;
import com.nashtech.rookies.model.AccountId;

public interface AccountRepository extends JpaRepository<Account, AccountId> {

	List<Account> findByAccountType(String accountType);
}