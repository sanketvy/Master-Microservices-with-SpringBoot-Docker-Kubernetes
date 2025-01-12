package com.learning.accounts.dto;

import com.learning.accounts.entity.Account;

public class AccountRequest {

    private String name;

    private String email;

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
