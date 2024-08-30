package com.eazybank.service;

import com.eazybank.entity.AccountTransaction;

import java.util.List;

public interface AccountTransactionService {

    public List<AccountTransaction> getBalanceDetails(int customerId);

}
