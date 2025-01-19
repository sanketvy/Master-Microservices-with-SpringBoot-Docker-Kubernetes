package com.learning.accounts.clients;

import com.learning.accounts.dto.LoanResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoansFallback implements LoansClient{

    @Override
    public List<LoanResponse> getLoanById(int id) {
        return null;
    }
}
