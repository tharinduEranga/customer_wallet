package com.example.hubpay.wallet.model;

import com.example.hubpay.wallet.model.value.CustomerId;
import com.example.hubpay.wallet.model.value.MonetaryAmount;
import com.example.hubpay.wallet.model.value.TransactionId;
import com.example.hubpay.wallet.model.value.WalletId;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.Objects;

public record DebitWalletResult(
        @Nonnull
        TransactionId transactionId,
        @Nonnull
        WalletId walletId,
        @Nonnull
        CustomerId customerId,
        @Nonnull
        MonetaryAmount amount,
        @Nullable
        String description
) {
    public DebitWalletResult {
        Objects.requireNonNull(transactionId, "transactionId cannot be null");
        Objects.requireNonNull(walletId, "walletId cannot be null");
        Objects.requireNonNull(customerId, "customerId cannot be null");
        Objects.requireNonNull(amount, "amount cannot be null");
    }


    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private @Nonnull TransactionId transactionId;
        private @Nonnull WalletId walletId;
        private @Nonnull CustomerId customerId;
        private @Nonnull MonetaryAmount amount;
        private @Nullable String description;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder transactionId(@Nonnull TransactionId val) {
            transactionId = val;
            return this;
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

        public DebitWalletResult build() {
            return new DebitWalletResult(transactionId, walletId, customerId, amount, description);
        }
    }
}
