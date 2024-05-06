package com.funpodium.blockchain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="btc_transaction")
public class BTCTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="transaction_id")
    private int transactionId;
    
    @Column(name="user_id")
    private int userId;

    @Column(name="btc_change")
    private int btcChange;
    
    @Column(name="btc_price")
    private int btcPrice;

    @Column(name="usd_balance")
    private int usdBalance;

    @Column(name="btc_balance")
    private int btcBalance;

    protected BTCTransaction(){}

    public BTCTransaction(int userId, int btcChange) {
        this.userId = userId;
        this.btcChange = btcChange;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public int getUserId() {
        return userId;
    }

    public int getBtcChange() {
        return btcChange;
    }

    public int getBtcPrice() {
        return btcPrice;
    }

    public void setTransactionId(int exchangeId) {
        this.transactionId = exchangeId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setBtcChange(int btcChange) {
        this.btcChange = btcChange;
    }

    public void setBtcPrice(int btcPrice) {
        this.btcPrice = btcPrice;
    }

    public int getUsdBalance() {
        return usdBalance;
    }

    public int getBtcBalance() {
        return btcBalance;
    }

    public void setUsdBalance(int usdBalance) {
        this.usdBalance = usdBalance;
    }

    public void setBtcBalance(int btcBalance) {
        this.btcBalance = btcBalance;
    }

}
