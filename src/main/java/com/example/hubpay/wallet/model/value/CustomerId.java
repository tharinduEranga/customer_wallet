package com.example.hubpay.wallet.model.value;

import jakarta.annotation.Nonnull;

import java.util.Objects;
import java.util.UUID;

public record CustomerId(
        @Nonnull
        UUID value
) {
    public CustomerId {
        Objects.requireNonNull(value, "customer id cannot be null");
    }

    public static CustomerId of(final UUID value) {
        return new CustomerId(value);
    }
}
