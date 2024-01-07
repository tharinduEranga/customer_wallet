package com.example.hubpay.wallet.api.response;

public record TransactionFetchResponse(
        String transactionId,
        String walletId,
        String customerId,
        String amount,
        String currency,
        String type,
        String balanceBefore,
        String balanceAfter,
        String createdAt,
        String description
) {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String transactionId;
        private String walletId;
        private String customerId;
        private String currency;
        private String amount;
        private String balanceBefore;
        private String balanceAfter;
        private String createdAt;
        private String description;
        private String type;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder transactionId(String val) {
            transactionId = val;
            return this;
        }

        public Builder walletId(String val) {
            walletId = val;
            return this;
        }

        public Builder customerId(String val) {
            customerId = val;
            return this;
        }

        public Builder currency(String val) {
            currency = val;
            return this;
        }

        public Builder amount(String val) {
            amount = val;
            return this;
        }

        public Builder balanceBefore(String val) {
            balanceBefore = val;
            return this;
        }

        public Builder balanceAfter(String val) {
            balanceAfter = val;
            return this;
        }

        public Builder createdAt(String val) {
            createdAt = val;
            return this;
        }

        public Builder description(String val) {
            description = val;
            return this;
        }

        public Builder type(String val) {
            type = val;
            return this;
        }

        public TransactionFetchResponse build() {
            return new TransactionFetchResponse(transactionId, walletId, customerId, amount, currency, type, balanceBefore, balanceAfter, createdAt, description);
        }
    }
}
