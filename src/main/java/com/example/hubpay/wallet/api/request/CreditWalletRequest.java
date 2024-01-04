package com.example.hubpay.wallet.api.request;

public record CreditWalletRequest(
        String walletId,
        String customerId,
        String amount,
        String description
) {
}
