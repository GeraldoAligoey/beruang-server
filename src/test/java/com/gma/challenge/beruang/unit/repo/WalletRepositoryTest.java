package com.gma.challenge.beruang.unit.repo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.gma.challenge.beruang.common.RepositoryTest;
import com.gma.challenge.beruang.domain.Category;
import com.gma.challenge.beruang.domain.Wallet;
import com.gma.challenge.beruang.repo.WalletRepository;

@DataJpaTest
public class WalletRepositoryTest implements RepositoryTest {

  private final Logger LOG = LoggerFactory.getLogger(WalletRepositoryTest.class);

  @Autowired
  private WalletRepository SUT;
  
  @Test
  @Override
  public void testFindAll() {
    List<Wallet> wallets = SUT.findAll(); 
    assertNotNull(wallets);
    assertTrue(wallets.size() > 1);
  }

  @Test
  @Override
  public void testCreate() {
    Wallet newWallet = new Wallet();
    newWallet.setName("test"); 
    newWallet.setInitialBalanceAmount(BigDecimal.ZERO); 
    newWallet.setDefaultCurrencyCode("MYR");
    newWallet.setDefaultWallet(true);

    Category c1 = new Category();
    c1.setId(Long.valueOf(1)); 

    Category c2 = new Category();
    c2.setId(Long.valueOf(2)); 

    newWallet.addCategory(c1);
    newWallet.addCategory(c2);

    Wallet wallet = SUT.save(newWallet);

    assertNotNull(wallet); 
    assertNotNull(wallet.getId()); 

    LOG.info("id: {}", wallet.getId());
  }

  @Test
  @Override
  public void testUpdate() {
    Wallet newWallet = new Wallet();
    newWallet.setName("test"); 
    newWallet.setInitialBalanceAmount(BigDecimal.ZERO); 
    newWallet.setDefaultCurrencyCode("MYR");
    newWallet.setDefaultWallet(true);
    newWallet.setCategories(null);

    Wallet savedWallet = SUT.save(newWallet);

    savedWallet.setName("Updated wallet name");

    Wallet updatedWallet = SUT.save(savedWallet);

    assertNotNull(updatedWallet);
    assertTrue(updatedWallet.getName().equals("Updated wallet name"));

    Wallet oldWallet = SUT.findById(Long.valueOf(1)).get();
    oldWallet.setInitialBalanceAmount(new BigDecimal(12345));

    Wallet updatedOldWallet = SUT.save(oldWallet);

    assertNotNull(updatedOldWallet);
    assertTrue(updatedOldWallet.getInitialBalanceAmount().compareTo(new BigDecimal(12345)) == 0);
  }

  @Test
  @Override
  public void testDelete() {
    Wallet newWallet = new Wallet();
    newWallet.setName("test"); 
    newWallet.setInitialBalanceAmount(BigDecimal.ZERO); 
    newWallet.setDefaultCurrencyCode("MYR");
    newWallet.setDefaultWallet(true);
    newWallet.setCategories(null);

    Wallet wallet = SUT.save(newWallet);

    SUT.delete(wallet);

    assertTrue(!SUT.findById(wallet.getId()).isPresent());
  }
  
}
