package com.example.hubpay.wallet.api.controller;

import com.example.hubpay.wallet.api.response.TransactionFetchResponse;
import com.example.hubpay.wallet.mapper.TransactionFetchMapper;
import com.example.hubpay.wallet.service.TransactionFetcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/transaction")
public class TransactionFetchController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionFetchController.class);

    private final TransactionFetcher transactionFetcher;

    public TransactionFetchController(final TransactionFetcher transactionFetcher) {
        this.transactionFetcher = transactionFetcher;
    }

    @GetMapping(path = "/fetch")
    public ResponseEntity<Page<TransactionFetchResponse>> fetchTransactions(Pageable pageable) {
        LOGGER.info("Fetch transaction request: page: {}", pageable);
        final var result = transactionFetcher.fetch(pageable);
        final var response = TransactionFetchMapper.toTransactionFetchResponse(result);
        LOGGER.info("Debit wallet response: {}", response);
        return ResponseEntity.ok(response);
    }
}
