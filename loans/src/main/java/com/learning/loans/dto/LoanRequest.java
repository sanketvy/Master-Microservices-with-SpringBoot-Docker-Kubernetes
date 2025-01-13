package com.learning.loans.dto;

import com.learning.loans.entity.Loan;

import java.time.LocalDate;

public class LoanRequest {
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

    public Loan toLoan() {
        Loan loan = new Loan();

        loan.setAccountId(this.getAccountId());
        loan.setLoanAmount(this.getLoanAmount());
        loan.setLoanTerm(this.getLoanTerm());
        loan.setLoanType(this.getLoanType());
        loan.setStartDate(this.getStartDate());

        return loan;
    }
}
