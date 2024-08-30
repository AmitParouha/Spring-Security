package com.eazybank.service.impl;

import com.eazybank.entity.Account;
import com.eazybank.repository.AccountRepository;
import com.eazybank.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository repository;

    @Override
    public Account getAccountByCustomerId(int customerId) {
        return repository.findByCustomerId(customerId);
    }

}
