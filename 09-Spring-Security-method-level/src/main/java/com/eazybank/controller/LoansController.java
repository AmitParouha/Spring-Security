package com.eazybank.controller;

import com.eazybank.entity.Loans;
import com.eazybank.service.LoanService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/loans")
public class LoansController {

    private LoanService loanService;

    @GetMapping("/myLoans/{customerId}")
    @PostAuthorize("hasRole('USER')")
    public ResponseEntity<List<Loans>> getLoansDetails(@PathVariable int customerId){
        List<Loans> loans = loanService.getAllLoansDetailsByCustomerId(customerId);
        if (loans != null){
            return ResponseEntity.ok(loans);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
