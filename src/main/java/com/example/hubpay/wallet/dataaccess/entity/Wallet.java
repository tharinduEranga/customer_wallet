package com.example.hubpay.wallet.dataaccess.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

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
    private String currency;

    private OffsetDateTime createdAt;

    /**
     * this is used by the database for optimistic locking
     */
    @Version
    @Column(columnDefinition="BIGINT default '0'")
    private Long version;

    public Wallet() {
    }

    public Wallet(UUID customerId, BigDecimal balance, String currency) {
        this.id = UUID.randomUUID();
        this.customerId = customerId;
        this.balance = balance;
        this.currency = currency;
        this.createdAt = OffsetDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void credit(BigDecimal amount) {
        this.balance = balance.add(amount);
    }

    public void debit(BigDecimal amount) {
        this.balance = balance.subtract(amount);
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
