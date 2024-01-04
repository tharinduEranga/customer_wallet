package com.example.hubpay.wallet.dataaccess.repository;

import com.example.hubpay.wallet.dataaccess.entity.CreditTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CreditTransactionRepository extends JpaRepository<CreditTransaction, UUID > {

}
