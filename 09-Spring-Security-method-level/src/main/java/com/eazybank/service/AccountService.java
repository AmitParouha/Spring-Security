package com.eazybank.service;

import com.eazybank.entity.Account;

public interface AccountService {

    public Account getAccountByCustomerId(int customerId);

}
