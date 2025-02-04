CREATE TABLE IF NOT EXISTS account (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE IF NOT EXISTS balance (
    user_id INT PRIMARY KEY,
    usd_balance INT NOT NULL,
    btc_balance INT NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE IF NOT EXISTS btc_transaction (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    btc_change INT NOT NULL,
    btc_price INT NOT NULL,
    usd_balance INT NOT NULL,
    btc_balance INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


-- CREATE TABLE IF NOT EXISTS account (
--     user_id INT AUTO_INCREMENT PRIMARY KEY,
--     username VARCHAR(50) NOT NULL UNIQUE COLLATE utf8mb4_unicode_ci,
--     email VARCHAR(100) NOT NULL COLLATE utf8mb4_unicode_ci,
--     usd_balance DECIMAL(10, 2) NOT NULL DEFAULT 1000.00,
--     btc_balance DECIMAL(18, 8) NOT NULL DEFAULT 0,
--     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
