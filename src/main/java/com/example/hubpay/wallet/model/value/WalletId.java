package com.example.hubpay.wallet.model.value;

import jakarta.annotation.Nonnull;

import java.util.Objects;
import java.util.UUID;

public record WalletId(
        @Nonnull
        UUID value
) {
    public WalletId {
        Objects.requireNonNull(value, "wallet id cannot be null");
    }

    public static WalletId of(final UUID value) {
        return new WalletId(value);
    }
}
