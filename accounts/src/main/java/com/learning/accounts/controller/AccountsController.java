package com.learning.accounts.controller;

import com.learning.accounts.dto.AccountRequest;
import com.learning.accounts.dto.AccountResponse;
import com.learning.accounts.entity.Account;
import com.learning.accounts.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AccountsController {

    AccountRepository accountRepository;

    @Autowired
    AccountsController(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @GetMapping("/mission")
    public Map<String, String> helloRoute(){
        Map<String, String> map = new HashMap<>();
        map.put("name","Mission 50");

        return map;
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
    public ResponseEntity<AccountResponse> addAccount(@RequestBody AccountRequest account){
        AccountResponse accountResponse = new AccountResponse();
        accountResponse.fromAccount(accountRepository.save(account.toAccount()));
        return ResponseEntity.status(200).body(accountResponse);
    }

}
