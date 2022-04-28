package com.gma.challenge.beruang.repo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.gma.challenge.beruang.model.Wallet;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Sql("classpath:test_wallet.sql")
public class WalletRepositoryTest {

  private static final Logger LOG = LoggerFactory.getLogger(WalletRepositoryTest.class);

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
