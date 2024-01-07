package com.example.hubpay.wallet.mapper;

import com.example.hubpay.wallet.api.request.CreditWalletRequest;
import com.example.hubpay.wallet.api.response.CreditWalletResponse;
import com.example.hubpay.wallet.dataaccess.entity.Transaction;
import com.example.hubpay.wallet.model.CreditWalletData;
import com.example.hubpay.wallet.model.CreditWalletResult;
import com.example.hubpay.wallet.model.value.CustomerId;
import com.example.hubpay.wallet.model.value.MonetaryAmount;
import com.example.hubpay.wallet.model.value.TransactionId;
import com.example.hubpay.wallet.model.value.WalletId;

import java.math.BigDecimal;
import java.util.UUID;

public class CreditWalletMapper {

    private CreditWalletMapper() {

    }

    public static CreditWalletData toCreditWalletData(CreditWalletRequest request) {
        return CreditWalletData.builder()
                .walletId(WalletId.of(UUID.fromString(request.walletId())))
                .customerId(CustomerId.of(UUID.fromString(request.customerId())))
                .amount(MonetaryAmount.of(new BigDecimal(request.amount()), request.currency()))
                .description(request.description())
                .build();
    }

    public static CreditWalletResult toCreditWalletResult(Transaction transaction) {
        return CreditWalletResult.builder()
                .transactionId(TransactionId.of(transaction.getId()))
                .walletId(WalletId.of(transaction.getWalletId()))
                .amount(MonetaryAmount.of(transaction.getAmount(), transaction.getCurrency()))
                .customerId(CustomerId.of(transaction.getCustomerId()))
                .description(transaction.getDescription())
                .build();
    }

    public static CreditWalletResponse toCreditWalletResponse(CreditWalletResult result) {
        return CreditWalletResponse.builder()
                .transactionId(result.transactionId().value().toString())
                .build();
    }
}
