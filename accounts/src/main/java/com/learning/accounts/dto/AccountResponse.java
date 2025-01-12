package com.learning.accounts.dto;

import com.learning.accounts.entity.Account;

public class AccountResponse {

    private int id;

    private String name;

    private String email;

    private String branch;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public AccountResponse fromAccount(Account acc){
        this.branch = acc.getBranch();
        this.email = acc.getEmail();
        this.name = acc.getName();
        this.id = acc.getId();

        return this;
    }
}
