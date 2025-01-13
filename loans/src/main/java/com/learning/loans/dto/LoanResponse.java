package com.learning.loans.dto;

import com.learning.loans.entity.Loan;

import java.time.LocalDate;

public class LoanResponse {

    private String loanType;

    private Double loanAmount;

    private LocalDate startDate;

    private Integer loanTerm;

    private Integer accountId;

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Integer getLoanTerm() {
        return loanTerm;
    }

    public void setLoanTerm(Integer loanTerm) {
        this.loanTerm = loanTerm;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public LoanResponse fromLoan(Loan loan) {

        this.accountId = loan.getAccountId();
        this.loanAmount = loan.getLoanAmount();
        this.loanTerm = loan.getLoanTerm();
        this.loanType = loan.getLoanType();
        this.startDate=loan.getStartDate();

        return this;
    }
}
