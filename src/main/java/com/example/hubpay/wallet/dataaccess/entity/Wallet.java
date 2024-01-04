package com.example.hubpay.wallet.dataaccess.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "wallet")
public class Wallet {
    @Id
    private UUID id;

    private UUID customerId;

    private BigDecimal balance;

    private OffsetDateTime createdAt;

    public Wallet() {
    }

    public Wallet(UUID customerId, BigDecimal balance) {
        this.id = UUID.randomUUID();
        this.customerId = customerId;
        this.balance = balance;
        this.createdAt = OffsetDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Wallet wallet)) return false;
        return id.equals(wallet.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
