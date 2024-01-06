package com.example.hubpay.wallet.api.request;

public record DebitWalletRequest(
        String walletId,
        String customerId,
        String currency,
        String amount,
        String description
) {
}
