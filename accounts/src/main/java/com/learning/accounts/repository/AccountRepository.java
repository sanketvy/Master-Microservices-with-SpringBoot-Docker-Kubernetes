package com.learning.accounts.repository;

import com.learning.accounts.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {


}
