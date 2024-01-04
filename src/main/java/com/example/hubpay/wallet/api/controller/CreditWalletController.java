package com.example.hubpay.wallet.api.controller;

import com.example.hubpay.wallet.api.request.CreditWalletRequest;
import com.example.hubpay.wallet.api.response.CreditWalletResponse;
import com.example.hubpay.wallet.mapper.CreditWalletMapper;
import com.example.hubpay.wallet.service.WalletCreditor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/credit-wallet")
public class CreditWalletController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreditWalletController.class);

    private final WalletCreditor walletCreditor;

    public CreditWalletController(final WalletCreditor walletCreditor) {
        this.walletCreditor = walletCreditor;
    }

    @PostMapping(path = "/credit")
    public ResponseEntity<CreditWalletResponse> creditWallet(@RequestBody CreditWalletRequest creditWalletRequest) {
        LOGGER.info("Credit wallet request: {}", creditWalletRequest);
        final var data = CreditWalletMapper.toCreditWalletData(creditWalletRequest);
        final var result = walletCreditor.execute(data);
        final var response = CreditWalletMapper.toCreditWalletResponse(result);
        LOGGER.info("Credit wallet response: {}", response);
        return ResponseEntity.ok(response);
    }
}
