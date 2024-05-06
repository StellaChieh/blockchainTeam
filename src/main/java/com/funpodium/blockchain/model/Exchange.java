package com.funpodium.blockchain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="exchange")
public class Exchange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="exchange_id")
    private int exchangeId;

    @Column(name="user_id")
    private int userId;

    @Column(name="exchange_type")
    private boolean exchangeType;

    @Column(name="btc_change")
    private int btcChange;
    
    @Column(name="btc_price")
    private int btcPrice;

    private int usdBalance;

    private int btcBalance;

    protected Exchange(){}

    public Exchange(int exchangeId, int userId, boolean exchangeType, int btcChange, int btcPrice) {
        this.exchangeId = exchangeId;
        this.userId = userId;
        this.exchangeType = exchangeType;
        this.btcChange = btcChange;
        this.btcPrice = btcPrice;
    }

    public int getExchangeId() {
        return exchangeId;
    }

    public int getUserId() {
        return userId;
    }

    public boolean isExchangeType() {
        return exchangeType;
    }

    public int getBtcChange() {
        return btcChange;
    }

    public int getBtcPrice() {
        return btcPrice;
    }

    public void setExchangeId(int exchangeId) {
        this.exchangeId = exchangeId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setExchangeType(boolean exchangeType) {
        this.exchangeType = exchangeType;
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
