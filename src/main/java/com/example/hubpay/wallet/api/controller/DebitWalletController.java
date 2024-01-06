package com.example.hubpay.wallet.api.controller;

import com.example.hubpay.wallet.api.request.DebitWalletRequest;
import com.example.hubpay.wallet.api.response.DebitWalletResponse;
import com.example.hubpay.wallet.mapper.DebitWalletMapper;
import com.example.hubpay.wallet.service.WalletDebitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/debit-wallet")
public class DebitWalletController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DebitWalletController.class);

    private final WalletDebitor walletDebitor;

    public DebitWalletController(final WalletDebitor walletDebitor) {
        this.walletDebitor = walletDebitor;
    }

    @PostMapping(path = "/debit")
    public ResponseEntity<DebitWalletResponse> debitWallet(@RequestBody DebitWalletRequest debitWalletRequest) {
        LOGGER.info("Debit wallet request: {}", debitWalletRequest);
        final var data = DebitWalletMapper.toDebitWalletData(debitWalletRequest);
        final var result = walletDebitor.execute(data);
        final var response = DebitWalletMapper.toDebitWalletResponse(result);
        LOGGER.info("Debit wallet response: {}", response);
        return ResponseEntity.ok(response);
    }
}
