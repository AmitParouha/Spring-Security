package com.eazybank.service;

import com.eazybank.entity.Loans;

import java.util.List;

public interface LoanService {

    public List<Loans> getAllLoansDetailsByCustomerId(int customerId);

}
