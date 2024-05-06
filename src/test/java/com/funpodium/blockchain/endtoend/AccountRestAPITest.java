package com.funpodium.blockchain.endtoend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import com.funpodium.blockchain.model.Account;
import com.funpodium.blockchain.model.Balance;
import com.funpodium.blockchain.repository.IBalanceRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(
  locations = "classpath:application-test.properties")
public class AccountRestAPITest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private IBalanceRepository balanceRepository;

    @Test
    void can_Create_New_Account_And_Get_Grant_1000() throws Exception {
        String username = "myusername";
        String email = "username@gmail.com";
        Account newAccount = new Account(username, email);
        ResponseEntity<Account> response = testRestTemplate.postForEntity("/api/v1/accounts", newAccount, Account.class);  
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(username, response.getBody().getUsername());
        assertEquals(email, response.getBody().getEmail());

        Optional<Balance> balance = balanceRepository.findById(response.getBody().getUserId());
        assertTrue(balance.isPresent());
        assertEquals(1000, balance.get().getUsdBalance());
        assertEquals(0, balance.get().getBtcBalance());
        
    }


}
