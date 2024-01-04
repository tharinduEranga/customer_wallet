package com.example.hubpay.wallet.model.value;

import jakarta.annotation.Nonnull;

import java.util.Objects;
import java.util.UUID;

public record TransactionId(
        @Nonnull
        UUID value
) {
    public TransactionId {
        Objects.requireNonNull(value, "wallet id cannot be null");
    }

    public static TransactionId of(final UUID value) {
        return new TransactionId(value);
    }
}
