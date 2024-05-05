package com.funpodium.blockchain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.funpodium.blockchain.controller.AccountController;

@SpringBootTest
class BlockchainApplicationTests {

	@Autowired
	private AccountController controller;

	@Test
	void contextLoads() throws Exception{
		assertThat(controller).isNotNull();
	}

}
