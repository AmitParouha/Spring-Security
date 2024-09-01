package com.eazybank.service.impl;

import com.eazybank.entity.AccountTransaction;
import com.eazybank.repository.AccountTransactionRepository;
import com.eazybank.service.AccountTransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AccountTransactionServiceImpl implements AccountTransactionService {

    private AccountTransactionRepository repository;

    @Override
    public List<AccountTransaction> getBalanceDetails(int customerId) {
        return repository.findByCustomerIdOrderByTransactionDtDesc(customerId);
    }
}
