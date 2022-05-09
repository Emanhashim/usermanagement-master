package com.bazra.usermanagement.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bazra.usermanagement.model.Transaction;


/**
 * Returns list of transaction of an account
 * @author Bemnet
 * @version 5/2022
 */

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
    List<Transaction> findByAccountNumberEquals(String accountnumber);
}

