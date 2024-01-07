package com.example.hubpay.wallet.mapper;

import com.example.hubpay.wallet.api.request.DebitWalletRequest;
import com.example.hubpay.wallet.api.response.DebitWalletResponse;
import com.example.hubpay.wallet.dataaccess.entity.Transaction;
import com.example.hubpay.wallet.model.DebitWalletData;
import com.example.hubpay.wallet.model.DebitWalletResult;
import com.example.hubpay.wallet.model.value.CustomerId;
import com.example.hubpay.wallet.model.value.MonetaryAmount;
import com.example.hubpay.wallet.model.value.TransactionId;
import com.example.hubpay.wallet.model.value.WalletId;

import java.math.BigDecimal;
import java.util.UUID;

public class DebitWalletMapper {

    private DebitWalletMapper() {

    }

    public static DebitWalletData toDebitWalletData(DebitWalletRequest request) {
        return DebitWalletData.builder()
                .walletId(WalletId.of(UUID.fromString(request.walletId())))
                .customerId(CustomerId.of(UUID.fromString(request.customerId())))
                .amount(MonetaryAmount.of(new BigDecimal(request.amount()), request.currency()))
                .description(request.description())
                .build();
    }

    public static DebitWalletResult toDebitWalletResult(Transaction transaction) {
        return DebitWalletResult.builder()
                .transactionId(TransactionId.of(transaction.getId()))
                .walletId(WalletId.of(transaction.getWalletId()))
                .amount(MonetaryAmount.of(transaction.getAmount(), transaction.getCurrency()))
                .customerId(CustomerId.of(transaction.getCustomerId()))
                .description(transaction.getDescription())
                .build();
    }

    public static DebitWalletResponse toDebitWalletResponse(DebitWalletResult result) {
        return DebitWalletResponse.builder()
                .transactionId(result.transactionId().value().toString())
                .build();
    }
}
