package com.gma.challenge.beruang.unit.repo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.gma.challenge.beruang.domain.Wallet;
import com.gma.challenge.beruang.repo.WalletRepository;

@DataJpaTest
public class WalletRepositoryTest {

  @Autowired
  private WalletRepository SUT;
  
  @Test
  public void testFindAll() {
    List<Wallet> wallets = SUT.findAll(); 
    assertNotNull(wallets);
    assertTrue(wallets.size() > 1);
  }
  
}
