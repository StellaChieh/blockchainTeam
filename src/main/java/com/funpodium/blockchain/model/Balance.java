package com.funpodium.blockchain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="balance")
public class Balance {

    @Id
    @Column(name="user_id")
    private int userId;

    @Column(name = "usd_balance")
    private int usdBalance;

    @Column(name = "btc_balance")
    private int btcBalance;

    // used for JPA default constructor
    protected Balance(){}

    public Balance(int userId, int usdBalance, int btcBalance) {
        this.userId = userId;
        this.usdBalance = usdBalance;
        this.btcBalance = btcBalance;
    }

    public int getUserId() {
        return userId;
    }

    public int getUsdBalance() {
        return usdBalance;
    }

    public int getBtcBalance() {
        return btcBalance;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUsdBalance(int usdBalance) {
        this.usdBalance = usdBalance;
    }

    public void setBtcBalance(int btcBalance) {
        this.btcBalance = btcBalance;
    }

    @Override
    public String toString() {
        return "Balance [userId=" + userId + ", usdBalance=" + usdBalance + ", btcBalance=" + btcBalance + "]";
    }

    
    
}
