package com.learning.accounts.clients;

import com.learning.accounts.dto.LoanResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "loans", fallback = LoansFallback.class)
public interface LoansClient{

    @GetMapping("/api/loan/account/{id}")
    List<LoanResponse> getLoanById(@PathVariable int id);
}
