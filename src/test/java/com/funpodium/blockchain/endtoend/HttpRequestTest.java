package com.funpodium.blockchain.endtoend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.funpodium.blockchain.model.Account;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void canCreateNewAccountAndGetGrant1000() throws Exception {
        String username = "myusername";
        String email = "username@gmail.com";
        Account newAccount = new Account(username, email);
        ResponseEntity<Account> response = testRestTemplate.postForEntity("/api/v1/accounts", newAccount, Account.class);  
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(username, response.getBody().getUsername());
        assertEquals(email, response.getBody().getEmail());
        assertEquals(BigDecimal.valueOf(1000), response.getBody().getUsdBalance());
        assertEquals(BigDecimal.valueOf(0), response.getBody().getBtcBalance());
        
    }


}
