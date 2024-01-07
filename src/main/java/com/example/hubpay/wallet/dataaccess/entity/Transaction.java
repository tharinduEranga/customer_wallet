package com.example.hubpay.wallet.dataaccess.entity;

import com.example.hubpay.wallet.model.TransactionFetchResult;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    private UUID id;

    private UUID walletId;
    private UUID customerId;

    private BigDecimal amount;
    private String currency;

    private BigDecimal balanceBefore;

    private BigDecimal balanceAfter;

    private OffsetDateTime createdAt;

    private String description;

    @Enumerated(EnumType.STRING)
    private TransactionFetchResult.TransactionType type;

    public Transaction() {
    }

    public Transaction(UUID walletId, UUID customerId, BigDecimal amount, String currency, BigDecimal balanceBefore, BigDecimal balanceAfter, OffsetDateTime createdAt, String description, TransactionFetchResult.TransactionType type) {
        this.id = UUID.randomUUID();
        this.walletId = walletId;
        this.customerId = customerId;
        this.amount = amount;
        this.currency = currency;
        this.balanceBefore = balanceBefore;
        this.balanceAfter = balanceAfter;
        this.createdAt = createdAt;
        this.description = description;
        this.type = type;
    }

    public UUID getId() {
        return id;
    }

    public UUID getWalletId() {
        return walletId;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getBalanceBefore() {
        return balanceBefore;
    }

    public BigDecimal getBalanceAfter() {
        return balanceAfter;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public String getDescription() {
        return description;
    }

    public TransactionFetchResult.TransactionType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction that)) return false;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private UUID walletId;
        private UUID customerId;
        private BigDecimal amount;
        private String currency;
        private BigDecimal balanceBefore;
        private BigDecimal balanceAfter;
        private OffsetDateTime createdAt;
        private String description;
        private TransactionFetchResult.TransactionType type;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder walletId(UUID val) {
            walletId = val;
            return this;
        }

        public Builder customerId(UUID val) {
            customerId = val;
            return this;
        }

        public Builder amount(BigDecimal val) {
            amount = val;
            return this;
        }

        public Builder currency(String val) {
            currency = val;
            return this;
        }

        public Builder balanceBefore(BigDecimal val) {
            balanceBefore = val;
            return this;
        }

        public Builder balanceAfter(BigDecimal val) {
            balanceAfter = val;
            return this;
        }

        public Builder createdAt(OffsetDateTime val) {
            createdAt = val;
            return this;
        }

        public Builder description(String val) {
            description = val;
            return this;
        }

        public Builder type(TransactionFetchResult.TransactionType val) {
            type = val;
            return this;
        }

        public Transaction build() {
            return new Transaction(walletId, customerId, amount, currency, balanceBefore, balanceAfter, createdAt, description, type);
        }
    }
}
