package com.eazybank.controller;

import com.eazybank.entity.AccountTransaction;
import com.eazybank.service.AccountTransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/balance")
public class BalanceController {

    private AccountTransactionService service;

    @GetMapping("/myBalance/{customerId}")
    public ResponseEntity<List<AccountTransaction>> getBalanceDetails(
            @PathVariable int customerId)
    {
        List<AccountTransaction> accountTransactions = service.getBalanceDetails(customerId);
        if (accountTransactions != null){
            return ResponseEntity.ok(accountTransactions);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
