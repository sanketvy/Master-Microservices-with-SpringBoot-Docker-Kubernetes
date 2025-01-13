package com.learning.loans.service;

import com.learning.loans.dto.LoanRequest;
import com.learning.loans.dto.LoanResponse;
import com.learning.loans.entity.Loan;
import com.learning.loans.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    LoanRepository loanRepository;

    @Autowired
    LoanService(LoanRepository loanRepository){
        this.loanRepository = loanRepository;
    }


    public LoanResponse getLoan(int id){
        Optional<Loan> loan = loanRepository.findById(id);
        if(loan.isPresent()){
            LoanResponse response = new LoanResponse();
            return response.fromLoan(loan.get());
        }
        else {
            throw new RuntimeException("Loan ID does not exists");
        }
    }

    public LoanResponse addLoan(LoanRequest loanRequest){
        Loan loan = loanRepository.save(loanRequest.toLoan());
        return new LoanResponse().fromLoan(loan);
    }

    public LoanResponse deleteLoan(int id){
        Optional<Loan> loan = loanRepository.findById(id);
        if(loan.isPresent()) {
            loanRepository.deleteById(id);
            return new LoanResponse().fromLoan(loan.get());
        } else {
            throw new RuntimeException("Loan ID does not exists");
        }
    }

    public LoanResponse updateLoan(int id, LoanRequest loanRequest){
        Loan loan = loanRequest.toLoan();
        loan.setId(id);
        loan = loanRepository.save(loan);
        return new LoanResponse().fromLoan(loan);
    }

    public List<LoanResponse> getAllLoanByAccountId(int id){
        List<Loan> loans = loanRepository.findByAccountId(id);
        List<LoanResponse> responses = new ArrayList<>();

        loans.forEach(loan -> {
            responses.add(new LoanResponse().fromLoan(loan));
        });

        return responses;
    }

}
