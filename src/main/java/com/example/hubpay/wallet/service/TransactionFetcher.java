package com.example.hubpay.wallet.service;

import com.example.hubpay.wallet.dataaccess.repository.TransactionRepository;
import com.example.hubpay.wallet.model.TransactionFetchResult;
import com.example.hubpay.wallet.model.value.CustomerId;
import com.example.hubpay.wallet.model.value.MonetaryAmount;
import com.example.hubpay.wallet.model.value.TransactionId;
import com.example.hubpay.wallet.model.value.WalletId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TransactionFetcher {
    private final TransactionRepository transactionRepository;

    public TransactionFetcher(final TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Page<TransactionFetchResult> fetch(final Pageable pageable) {
        return transactionRepository.findAll(pageable)
                .map(transaction -> TransactionFetchResult.builder()
                        .transactionId(TransactionId.of(transaction.getId()))
                        .walletId(WalletId.of(transaction.getWalletId()))
                        .customerId(CustomerId.of(transaction.getCustomerId()))
                        .amount(MonetaryAmount.of(transaction.getAmount(), transaction.getCurrency()))
                        .balanceBefore(MonetaryAmount.of(transaction.getBalanceBefore(), transaction.getCurrency()))
                        .balanceAfter(MonetaryAmount.of(transaction.getBalanceAfter(), transaction.getCurrency()))
                        .createdAt(transaction.getCreatedAt())
                        .description(transaction.getDescription())
                        .type(transaction.getType())
                        .build());
    }
}
