package com.gma.challenge.beruang.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.gma.challenge.beruang.model.Wallet;
import com.gma.challenge.beruang.repo.WalletRepository;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql("classpath:test_wallet.sql")
public class ReadWalletServiceImplTest {

  private static final Logger LOG = LoggerFactory.getLogger(ReadWalletServiceImplTest.class);

  @Autowired
  private WalletRepository walletRepository;

  @Test
  public void findAll() {
    List<Wallet> wallets = walletRepository.findAll();

    for (Wallet wallet : wallets) {
      LOG.info("Wallet id: {}; name: {}", wallet.getId(), wallet.getName());
    }
    assertTrue(wallets.size() == 3);
  }
  
}
