CREATE TABLE IF NOT EXISTS customer(
    id VARCHAR(20) PRIMARY KEY,
    national_id VARCHAR(20) NOT NULL,
    name VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS wallet(
    id VARCHAR(20) PRIMARY KEY,
    customer_id VARCHAR(20) NOT NULL,
    balance DECIMAL(10, 2) NOT NULL,
    currency VARCHAR(10) NOT NULL,
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS credit_transaction(
    id VARCHAR(20) PRIMARY KEY,
    wallet_id VARCHAR(20) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    currency VARCHAR(10) NOT NULL,
    balance_before DECIMAL(10, 2) NOT NULL,
    balance_after DECIMAL(10, 2) NOT NULL,
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS debit_transaction(
    id VARCHAR(20) PRIMARY KEY,
    wallet_id VARCHAR(20) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    currency VARCHAR(10) NOT NULL,
    balance_before DECIMAL(10, 2) NOT NULL,
    balance_after DECIMAL(10, 2) NOT NULL,
    created_at TIMESTAMP NOT NULL
);
