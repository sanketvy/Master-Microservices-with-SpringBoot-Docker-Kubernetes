package com.learning.accounts.controller;

import com.learning.accounts.entity.Account;
import com.learning.accounts.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AccountsController {

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/mission")
    public Map<String, String> helloRoute(){
        Map<String, String> map = new HashMap<>();
        map.put("name","Mission 50");

        return map;
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable String id){
        System.out.println(id);
        return ResponseEntity.status(200).body(accountRepository.findById(Integer.parseInt(id)).get());
    }

}
