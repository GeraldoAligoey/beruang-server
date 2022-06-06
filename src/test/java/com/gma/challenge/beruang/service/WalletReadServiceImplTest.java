package com.gma.challenge.beruang.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import com.gma.challenge.beruang.data.WalletData;
import com.gma.challenge.beruang.data.WalletResponseData;
import com.gma.challenge.beruang.data.WalletsResponseData;
import com.gma.challenge.beruang.exception.WalletNotFoundException;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class WalletReadServiceImplTest {

  private static final Long VALID_ID = 1l;
  private static final Long VALID_NORECORD_ID = 99999l;
  private static final Long INVALID_ID = -1l;

  @Autowired
  private WalletReadServiceImpl SUT;

  @Test
  @Sql("classpath:sql/testFind_empty.sql")
  public void testFindWallets_empty() {
    WalletsResponseData walletsResponseData = SUT.findWallets();
    List<WalletData> wallets = walletsResponseData.getWallets();

    assertNotNull(wallets);
    assertTrue(wallets.isEmpty());
  }

  @Test
  @Sql({"classpath:sql/testFind_empty.sql", "classpath:sql/testFindWallets_singleItem.sql"})
  public void testFindWallets_singleItem() {
    WalletsResponseData walletsResponseData = SUT.findWallets();
    List<WalletData> wallets = walletsResponseData.getWallets();

    assertNotNull(wallets);
    assertTrue(wallets.size() == 1);
  }
  
  @Test
  @Sql({"classpath:sql/testFind_empty.sql", "classpath:sql/testFindWallets_multipleItems.sql"})
  public void testFindWallets_multipleItems() {
    WalletsResponseData walletsResponseData = SUT.findWallets();
    List<WalletData> wallets = walletsResponseData.getWallets();

    assertNotNull(wallets);
    assertTrue(wallets.size() > 1);
  }

  @Test
  public void testFindWallet_validId_RecordExist() {
    WalletResponseData walletResponseData = SUT.findWallet(VALID_ID);
    assertNotNull(walletResponseData.getWallet());
  }

  @Test
  public void testFindWallet_validId_RecordNotExist() {
    assertThrows(WalletNotFoundException.class, () -> SUT.findWallet(VALID_NORECORD_ID));
  }

  @Test
  public void testFindWallet_invalidId() {
    assertThrows(WalletNotFoundException.class, () -> SUT.findWallet(INVALID_ID));
  }
}
