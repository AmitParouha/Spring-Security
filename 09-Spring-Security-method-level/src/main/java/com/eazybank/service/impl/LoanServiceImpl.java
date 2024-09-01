package com.eazybank.service.impl;

import com.eazybank.entity.Loans;
import com.eazybank.repository.LoanRepository;
import com.eazybank.service.LoanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class LoanServiceImpl implements LoanService {

    private LoanRepository loanRepository;

    @Override
    public List<Loans> getAllLoansDetailsByCustomerId(int customerId) {
        return loanRepository.findByCustomerIdOrderByStartDtDesc(customerId);
    }
}
