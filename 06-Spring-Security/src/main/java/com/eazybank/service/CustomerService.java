package com.eazybank.service;

import com.eazybank.entity.Customer;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface CustomerService {

    public Customer saveCustomerDetails(Customer customer);
    public List<Customer> getCustomerDetails(Authentication authentication);

}
