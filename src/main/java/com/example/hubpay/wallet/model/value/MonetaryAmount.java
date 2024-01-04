package com.example.hubpay.wallet.model.value;

import jakarta.annotation.Nonnull;

import java.math.BigDecimal;
import java.util.Objects;

public record MonetaryAmount(
        @Nonnull
        BigDecimal amount
) {
    public MonetaryAmount {
        Objects.requireNonNull(amount, "amount cannot be null");
    }

    public static MonetaryAmount of(BigDecimal value) {
        return new MonetaryAmount(value);
    }
}
