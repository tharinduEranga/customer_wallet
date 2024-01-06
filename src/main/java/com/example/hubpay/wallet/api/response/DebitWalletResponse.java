package com.example.hubpay.wallet.api.response;

public record DebitWalletResponse(
        String transactionId
) {


    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String transactionId;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder transactionId(String val) {
            transactionId = val;
            return this;
        }

        public DebitWalletResponse build() {
            return new DebitWalletResponse(transactionId);
        }
    }
}
