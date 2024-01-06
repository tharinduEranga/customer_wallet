package com.example.hubpay.wallet.model.value;

import jakarta.annotation.Nonnull;

import java.math.BigDecimal;
import java.util.Objects;

public record MonetaryAmount(
        @Nonnull
        BigDecimal amount,
        @Nonnull
        String currency
) {
    public MonetaryAmount {
        Objects.requireNonNull(amount, "amount cannot be null");
        Objects.requireNonNull(currency, "currency cannot be null");
    }

    public static MonetaryAmount of(BigDecimal value, String currency) {
        return new MonetaryAmount(value, currency);
    }
}
