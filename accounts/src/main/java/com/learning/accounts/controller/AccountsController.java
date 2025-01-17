package com.learning.accounts.controller;

import com.learning.accounts.dto.AccountRequest;
import com.learning.accounts.dto.AccountResponse;
import com.learning.accounts.dto.ErrorResponse;
import com.learning.accounts.dto.LoanResponse;
import com.learning.accounts.entity.Account;
import com.learning.accounts.repository.AccountRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Validated
@Tag(name = "Accounts Controller", description = "This controller exposes Account related MS")
public class AccountsController {

    AccountRepository accountRepository;

    RestTemplate restTemplate;

    @Autowired
    AccountsController(AccountRepository accountRepository, RestTemplate restTemplate){
        this.accountRepository = accountRepository;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/account/{id}")
    @Operation(summary = "GET Account", description = "Fetch Account Data based on Account ID")
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "400", description = "BAD Request", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public ResponseEntity<AccountResponse> getAccount(@PathVariable String id){
        if(accountRepository.findById(Integer.parseInt(id)).isPresent()) {
            AccountResponse accountResponse = new AccountResponse();
            accountResponse.fromAccount(accountRepository.findById(Integer.parseInt(id)).get());
            List<LoanResponse> loanResponse = restTemplate.getForObject("http://localhost:8082/api/loan/account/1", List.class);
            accountResponse.setLoanResponse(loanResponse);
            return ResponseEntity.status(200).body(accountResponse);
        } else {
            throw new RuntimeException("Invalid Data. Please try again.");
        }
    }

    @PostMapping("/account")
    public ResponseEntity<AccountResponse> addAccount(@Valid @RequestBody AccountRequest account){
        AccountResponse accountResponse = new AccountResponse();
        accountResponse.fromAccount(accountRepository.save(account.toAccount()));
        return ResponseEntity.status(HttpStatus.CREATED).body(accountResponse);
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
