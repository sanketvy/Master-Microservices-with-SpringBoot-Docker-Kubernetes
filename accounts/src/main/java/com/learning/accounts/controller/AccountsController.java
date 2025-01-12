package com.learning.accounts.controller;

import com.learning.accounts.dto.AccountRequest;
import com.learning.accounts.dto.AccountResponse;
import com.learning.accounts.entity.Account;
import com.learning.accounts.repository.AccountRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Validated
public class AccountsController {

    AccountRepository accountRepository;

    @Autowired
    AccountsController(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable String id){
        if(accountRepository.findById(Integer.parseInt(id)).isPresent()) {
            return ResponseEntity.status(200).body(accountRepository.findById(Integer.parseInt(id)).get());
        } else {
            throw new RuntimeException("Invalid Data. Please try again.");
        }
    }

    @PostMapping("/account")
    public ResponseEntity<AccountResponse> addAccount(@Valid @RequestBody AccountRequest account){
        AccountResponse accountResponse = new AccountResponse();
        accountResponse.fromAccount(accountRepository.save(account.toAccount()));
        return ResponseEntity.status(200).body(accountResponse);
    }

    @PutMapping("/account/{id}")
    public ResponseEntity<AccountResponse> updateAccount(@PathVariable int id,@RequestBody AccountRequest accountRequest){
        Account updatedAccount = accountRequest.toAccount();
        updatedAccount.setId(id);

        AccountResponse response = new AccountResponse();
        return ResponseEntity.status(200).body(response.fromAccount(accountRepository.save(updatedAccount)));
    }

    @DeleteMapping("/account/{id}")
    public ResponseEntity<AccountResponse> deleteAccount(@PathVariable int id){
        Optional<Account> account = accountRepository.findById(id);
        if(account.isPresent()){
            accountRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new AccountResponse().fromAccount(account.get()));
        } else {
            throw new RuntimeException("Account does not exists");
        }
    }
}
