package com.example.hubpay.wallet.model;

import com.example.hubpay.wallet.model.value.CustomerId;
import com.example.hubpay.wallet.model.value.MonetaryAmount;
import com.example.hubpay.wallet.model.value.WalletId;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.Objects;

public record CreditWalletData(
        @Nonnull
        WalletId walletId,
        @Nonnull
        CustomerId customerId,
        @Nonnull
        MonetaryAmount amount,
        @Nullable
        String description
) {
    public CreditWalletData {
        Objects.requireNonNull(walletId, "walletId cannot be null");
        Objects.requireNonNull(customerId, "customerId cannot be null");
        Objects.requireNonNull(amount, "amount cannot be null");
    }


    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private @Nonnull WalletId walletId;
        private @Nonnull CustomerId customerId;
        private @Nonnull MonetaryAmount amount;
        private @Nullable String description;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder walletId(@Nonnull WalletId val) {
            walletId = val;
            return this;
        }

        public Builder customerId(@Nonnull CustomerId val) {
            customerId = val;
            return this;
        }

        public Builder amount(@Nonnull MonetaryAmount val) {
            amount = val;
            return this;
        }

        public Builder description(@Nullable String val) {
            description = val;
            return this;
        }

        public CreditWalletData build() {
            return new CreditWalletData(walletId, customerId, amount, description);
        }
    }
}
