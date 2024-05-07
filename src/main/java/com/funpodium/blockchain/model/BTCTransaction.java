package com.funpodium.blockchain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="btc_transaction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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

    public BTCTransaction(int userId, int btcChange) {
        this.userId = userId;
        this.btcChange = btcChange;
    }


}
