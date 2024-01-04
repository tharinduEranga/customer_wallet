package com.example.hubpay.wallet.service;

import com.example.hubpay.wallet.dataaccess.entity.CreditTransaction;
import com.example.hubpay.wallet.dataaccess.repository.CreditTransactionRepository;
import com.example.hubpay.wallet.dataaccess.repository.CustomerRepository;
import com.example.hubpay.wallet.dataaccess.repository.WalletRepository;
import com.example.hubpay.wallet.exception.custom.BusinessRuleViolationException;
import com.example.hubpay.wallet.exception.custom.NotFoundException;
import com.example.hubpay.wallet.mapper.CreditWalletMapper;
import com.example.hubpay.wallet.model.CreditWalletData;
import com.example.hubpay.wallet.model.CreditWalletResult;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Service
public class WalletCreditor {

    private static final BigDecimal MIN_AMOUNT = new BigDecimal("10");
    private static final BigDecimal MAX_AMOUNT = new BigDecimal("10000");

    private final CreditTransactionRepository creditTransactionRepository;
    private final WalletRepository walletRepository;
    private final CustomerRepository customerRepository;

    public WalletCreditor(final CreditTransactionRepository creditTransactionRepository,
                          final WalletRepository walletRepository, CustomerRepository customerRepository) {
        this.creditTransactionRepository = creditTransactionRepository;
        this.walletRepository = walletRepository;
        this.customerRepository = customerRepository;
    }

    public CreditWalletResult execute(CreditWalletData creditWalletData) {
        // validate customer
        if (!customerRepository.existsById(creditWalletData.customerId().value())) {
            throw new NotFoundException("Customer not found for id: " + creditWalletData.customerId());
        }

        // validate wallet
        final var wallet = walletRepository.findById(creditWalletData.walletId().value())
                .orElseThrow(() -> new NotFoundException("Wallet with id: %s not found!".formatted(creditWalletData.walletId())));

        // validate amount (The maximum amount is £10,000 & the minimum amount is £10)
        if (creditWalletData.amount().amount().compareTo(MIN_AMOUNT) < 0) {
            throw new BusinessRuleViolationException("Min amount should be equal or greater than " + MIN_AMOUNT);
        }
        if (creditWalletData.amount().amount().compareTo(MAX_AMOUNT) > 0) {
            throw new BusinessRuleViolationException("Max amount should be equal or less than " + MAX_AMOUNT);
        }

        var transaction = CreditTransaction.builder()
                .walletId(creditWalletData.walletId().value())
                .amount(creditWalletData.amount().amount())
                .createdAt(OffsetDateTime.now())
                .balanceBefore(wallet.getBalance())
                .balanceAfter(wallet.getBalance().add(creditWalletData.amount().amount()))
                .customerId(creditWalletData.customerId().value())
                .build();
        transaction = creditTransactionRepository.save(transaction);
        return CreditWalletMapper.toCreditWalletResult(transaction);
    }
}
