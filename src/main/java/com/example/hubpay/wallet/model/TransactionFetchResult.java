package com.example.hubpay.wallet.model;

import com.example.hubpay.wallet.model.value.CustomerId;
import com.example.hubpay.wallet.model.value.MonetaryAmount;
import com.example.hubpay.wallet.model.value.TransactionId;
import com.example.hubpay.wallet.model.value.WalletId;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.time.OffsetDateTime;

public record TransactionFetchResult(
        @Nonnull
        TransactionId transactionId,
        @Nonnull
        WalletId walletId,
        @Nonnull
        CustomerId customerId,
        @Nonnull
        MonetaryAmount amount,
        @Nonnull
        TransactionType type,
        @Nonnull
        MonetaryAmount balanceBefore,
        @Nonnull
        MonetaryAmount balanceAfter,
        @Nonnull
        OffsetDateTime createdAt,
        @Nullable
        String description
) {


    public static Builder builder() {
        return new Builder();
    }

    public enum TransactionType {
        CREDIT("Credit"), DEBIT("Debit");

        public final String type;

        TransactionType(final String type) {
            this.type = type;
        }
    }

    public static final class Builder {
        private @Nonnull TransactionId transactionId;
        private @Nonnull WalletId walletId;
        private @Nonnull CustomerId customerId;
        private @Nonnull MonetaryAmount amount;
        private @Nonnull TransactionType type;
        private @Nonnull MonetaryAmount balanceBefore;
        private @Nonnull MonetaryAmount balanceAfter;
        private @Nonnull OffsetDateTime createdAt;
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

        public Builder type(@Nonnull TransactionType val) {
            type = val;
            return this;
        }

        public Builder balanceBefore(@Nonnull MonetaryAmount val) {
            balanceBefore = val;
            return this;
        }

        public Builder balanceAfter(@Nonnull MonetaryAmount val) {
            balanceAfter = val;
            return this;
        }

        public Builder createdAt(@Nonnull OffsetDateTime val) {
            createdAt = val;
            return this;
        }

        public Builder description(@Nullable String val) {
            description = val;
            return this;
        }

        public TransactionFetchResult build() {
            return new TransactionFetchResult(transactionId, walletId, customerId, amount, type, balanceBefore, balanceAfter, createdAt, description);
        }
    }
}
