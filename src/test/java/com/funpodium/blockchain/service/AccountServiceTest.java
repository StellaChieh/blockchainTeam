package com.funpodium.blockchain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.funpodium.blockchain.model.Account;
import com.funpodium.blockchain.repository.IAccountRepository;
import com.funpodium.blockchain.repository.IBalanceRepository;
import com.funpodium.blockchain.repository.IBtcTransactionRepository;

@SpringBootTest
@TestPropertySource(
  locations = "classpath:application-test.properties")
public class AccountServiceTest {

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private IBtcTransactionRepository btcTransactionRepository;

    @Autowired
    private IBalanceRepository balanceRepository;

    @Test
	void contextLoads() throws Exception{
		assertThat(accountService).isNotNull();
        assertThat(accountRepository).isNotNull();
        assertThat(btcTransactionRepository).isNotNull();
        assertThat(balanceRepository).isNotNull();
	}

    @Test
    void user_can_delete_own_account(){
        String username = "foo";
        String email = "foo@gmail.com";
        Account account = this.accountService.createAccount(new Account(username, email));
        int userId = account.getUserId();
        this.accountService.deleteAccount(userId);

        assertTrue(this.accountRepository.findById(userId).isEmpty());
        assertTrue(this.balanceRepository.findById(userId).isEmpty());
        assertEquals(0, this.btcTransactionRepository.findByUserId(userId).size());

    }

}
