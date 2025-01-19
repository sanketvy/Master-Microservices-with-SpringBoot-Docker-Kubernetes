package com.learning.loans.controller;

import com.learning.loans.dto.LoanRequest;
import com.learning.loans.dto.LoanResponse;
import com.learning.loans.service.LoanService;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LoanController {

    LoanService loanService;

    @Autowired
    LoanController(LoanService loanService){
        this.loanService = loanService;
    }

    @Retry(name = "getLoan", fallbackMethod = "getLoanFallback")
    @GetMapping("/loan/{id}")
    public ResponseEntity<LoanResponse> getLoan(@PathVariable int id){
        System.out.println("Invoked");
//        throw new RuntimeException("Test");
        return ResponseEntity.status(HttpStatus.OK).body(loanService.getLoan(id));
    }

    public ResponseEntity<LoanResponse> getLoanFallback(@PathVariable int id, Throwable thr) {
        System.out.println("Fallback Invoked");

        return ResponseEntity.status(404).body(null);
    }

    @GetMapping("/loan/account/{id}")
    public ResponseEntity<List<LoanResponse>> getAllLoanByAccountId(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(loanService.getAllLoanByAccountId(id));
    }

    @PostMapping("/loan")
    public ResponseEntity<LoanResponse> addLoan(@RequestBody LoanRequest loanRequest){
        return ResponseEntity.status(HttpStatus.OK).body(loanService.addLoan(loanRequest));
    }

    @PutMapping("/loan/{id}")
    public ResponseEntity<LoanResponse> updateLoan(@PathVariable int id, @RequestBody LoanRequest loanRequest){
        return ResponseEntity.status(HttpStatus.OK).body(loanService.updateLoan(id, loanRequest));
    }

    @DeleteMapping("/loan/{id}")
    public ResponseEntity<LoanResponse> deleteLoan(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(loanService.deleteLoan(id));
    }
}
