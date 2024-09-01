package com.eazybank.service.impl;

import com.eazybank.entity.Customer;
import com.eazybank.repository.CustomerRepository;
import com.eazybank.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public Customer saveCustomerDetails(Customer customer) {
        String hashPwd = passwordEncoder.encode(customer.getPwd());
        customer.setPwd(hashPwd);
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getCustomerDetails(Authentication authentication) {
        if(authentication != null){
            String usernameOrEmail = authentication.getName();
            Optional<List<Customer>> customers = customerRepository
                    .findByEmailOrUsername(usernameOrEmail, usernameOrEmail);

            if (!customers.isEmpty()){
                return customers.get();
            }
        }
        return null;
    }
}
