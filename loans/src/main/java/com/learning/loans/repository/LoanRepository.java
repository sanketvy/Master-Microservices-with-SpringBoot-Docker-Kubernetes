package com.learning.loans.repository;

import com.learning.loans.dto.LoanResponse;
import com.learning.loans.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Integer> {

    List<Loan> findByAccountId(int id);
}
