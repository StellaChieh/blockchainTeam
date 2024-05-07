package com.funpodium.blockchain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="balance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Balance {

    @Id
    @Column(name="user_id")
    private int userId;

    @Column(name = "usd_balance")
    private int usdBalance;

    @Column(name = "btc_balance")
    private int btcBalance;
    
}
