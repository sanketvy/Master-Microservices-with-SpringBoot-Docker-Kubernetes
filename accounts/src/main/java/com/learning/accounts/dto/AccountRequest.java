package com.learning.accounts.dto;

import com.learning.accounts.entity.Account;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


@Schema(name = "AccountRequest", description = "Account Request DTO")
public class AccountRequest {

    private String name;

    @NotEmpty(message = "Email address cannot be empty")
    @Email(message = "Please enter valid email")
    private String email;

    @NotEmpty(message = "Branch code is mandatory")
    @NotNull(message = "Branch code is mandatory")
    @Pattern(regexp = "[a-zA-Z]+", message = "Please enter valid branch code.")
    private String branch;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Account toAccount() {
        Account account = new Account();
        account.setBranch(this.branch);
        account.setEmail(this.email);
        account.setName(this.name);
        return account;
    }
}
