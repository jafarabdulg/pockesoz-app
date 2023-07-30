package com.jafar.pockesoz.repository;

import com.jafar.pockesoz.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
