package com.example.hubpay.wallet.mapper;

import com.example.hubpay.wallet.api.response.TransactionFetchResponse;
import com.example.hubpay.wallet.model.TransactionFetchResult;
import org.springframework.data.domain.Page;

public class TransactionFetchMapper {

    private TransactionFetchMapper() {
    }

    public static Page<TransactionFetchResponse> toTransactionFetchResponse(final Page<TransactionFetchResult> data) {
        return data.map(transaction-> TransactionFetchResponse.builder()
                .transactionId(transaction.transactionId().value().toString())
                .walletId(transaction.walletId().value().toString())
                .customerId(transaction.customerId().value().toString())
                .amount(transaction.amount().amount().toString())
                .balanceBefore(transaction.balanceAfter().amount().toString())
                .balanceAfter(transaction.balanceBefore().amount().toString())
                .currency(transaction.amount().currency())
                .createdAt(transaction.createdAt().toString())
                .description(transaction.description())
                .type(transaction.type().type)
                .build());
    }
}
