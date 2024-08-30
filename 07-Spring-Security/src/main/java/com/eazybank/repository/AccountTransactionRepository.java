package com.eazybank.repository;

import com.eazybank.entity.AccountTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountTransactionRepository extends JpaRepository<AccountTransaction,Long> {

    List<AccountTransaction> findByCustomerIdOrderByTransactionDtDesc(int customerId);

}
