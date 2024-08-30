package com.eazybank.controller;

import com.eazybank.entity.Customer;
import com.eazybank.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private CustomerService service;

    @PostMapping(value = {"/register","sign-up"})
    public ResponseEntity<String> registerCustomer(@RequestBody Customer customer){
        Customer savedCustomer = null;
        ResponseEntity response = null;
        try{
            customer.setRole("ROLE_USER");
            customer.setCreateDt(new Date(System.currentTimeMillis()).toString());
            savedCustomer = service.saveCustomerDetails(customer);
            if(savedCustomer.getId()>0){
                response = ResponseEntity.status(HttpStatus.CREATED)
                        .body("Your account is created");
            }
        }catch (Exception ex){
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occurred due to: "+ex.getMessage());
        }

        return response;
    }

    @GetMapping("/user")
    public Customer getUserDetailsAfterLogin(Authentication authentication){
        List<Customer> customers = service.getCustomerDetails(authentication);
        if(customers != null){
            return customers.get(0);
        }
        return null;
    }

    @DeleteMapping("/delete")
    public String delete(){
        return "Deleted";
    }
}