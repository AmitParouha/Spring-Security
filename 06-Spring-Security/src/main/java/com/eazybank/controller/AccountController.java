package com.eazybank.controller;

import com.eazybank.entity.Account;
import com.eazybank.service.impl.AccountServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/account")
public class AccountController {

    private AccountServiceImpl accountService;

    @GetMapping("/myAccount/{customerId}")
    public ResponseEntity<Account> getAccountDetails(@PathVariable int customerId){
        Account account = accountService.getAccountByCustomerId(customerId);
        if(account == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.ok(account);
    }
}
