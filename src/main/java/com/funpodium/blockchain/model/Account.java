package com.funpodium.blockchain.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private int userId;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "usd_balance")
    private BigDecimal usdBalance = new BigDecimal(1000);

    @Column(name = "btc_balance")
    private BigDecimal btcBalance = new BigDecimal(0);

    
    // JPA default constructor only, so the scope is protected
    protected Account(){}

    
    public Account(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getUsdBalance() {
        return usdBalance;
    }

    public void setUsdBalance(BigDecimal usdBalance) {
        this.usdBalance = usdBalance;
    }

    public BigDecimal getBtcBalance() {
        return btcBalance;
    }

    public void setBtcBalance(BigDecimal btcBalance) {
        this.btcBalance = btcBalance;
    }


    @Override
    public String toString() {
        return "Account [userId=" + userId + ", username=" + username + ", email=" + email + ", usdBalance="
                + usdBalance + ", btcBalance=" + btcBalance + "]";
    }


    
    
}
