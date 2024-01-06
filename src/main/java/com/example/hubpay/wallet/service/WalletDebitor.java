package com.example.hubpay.wallet.service;

import com.example.hubpay.wallet.dataaccess.entity.DebitTransaction;
import com.example.hubpay.wallet.dataaccess.repository.DebitTransactionRepository;
import com.example.hubpay.wallet.dataaccess.repository.CustomerRepository;
import com.example.hubpay.wallet.dataaccess.repository.WalletRepository;
import com.example.hubpay.wallet.exception.custom.BusinessRuleViolationException;
import com.example.hubpay.wallet.exception.custom.NotFoundException;
import com.example.hubpay.wallet.mapper.DebitWalletMapper;
import com.example.hubpay.wallet.model.DebitWalletData;
import com.example.hubpay.wallet.model.DebitWalletResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Service
public class WalletDebitor {

    private static final BigDecimal MAX_AMOUNT = new BigDecimal("5000");
    private static final String CURRENCY_EUR = "EUR";

    private final DebitTransactionRepository debitTransactionRepository;
    private final WalletRepository walletRepository;
    private final CustomerRepository customerRepository;

    public WalletDebitor(final DebitTransactionRepository debitTransactionRepository,
                         final WalletRepository walletRepository,
                         final CustomerRepository customerRepository) {
        this.debitTransactionRepository = debitTransactionRepository;
        this.walletRepository = walletRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public DebitWalletResult execute(DebitWalletData debitWalletData) {
        // validate currency
        if (!CURRENCY_EUR.equals(debitWalletData.amount().currency())) {
            throw new BusinessRuleViolationException("Currency should be " + CURRENCY_EUR);
        }

        // validate amount (The maximum amount is 5,000)
        if (debitWalletData.amount().amount().compareTo(MAX_AMOUNT) > 0) {
            throw new BusinessRuleViolationException("Max amount should be equal or less than " + MAX_AMOUNT);
        }

        // validate customer
        if (!customerRepository.existsById(debitWalletData.customerId().value())) {
            throw new NotFoundException("Customer not found for id: " + debitWalletData.customerId().value());
        }

        // validate wallet and balance
        final var wallet = walletRepository.findById(debitWalletData.walletId().value())
                .orElseThrow(() -> new NotFoundException("Wallet with id: %s not found!".formatted(debitWalletData.walletId().value())));
        if (wallet.getBalance().compareTo(debitWalletData.amount().amount()) < 0) {
            throw new BusinessRuleViolationException("Not enough balance available for the transaction!");
        }

        var transaction = DebitTransaction.builder()
                .walletId(debitWalletData.walletId().value())
                .amount(debitWalletData.amount().amount())
                .currency(debitWalletData.amount().currency())
                .createdAt(OffsetDateTime.now())
                .balanceBefore(wallet.getBalance())
                .balanceAfter(wallet.getBalance().add(debitWalletData.amount().amount()))
                .customerId(debitWalletData.customerId().value())
                .build();
        transaction = debitTransactionRepository.save(transaction);

        wallet.debit(transaction.getAmount());
        walletRepository.save(wallet);

        return DebitWalletMapper.toDebitWalletResult(transaction);
    }
}
