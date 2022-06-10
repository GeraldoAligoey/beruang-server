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
public class WalletReadServiceImplTest implements ReadServiceTest {

  private static final Long VALID_ID = 1l;
  private static final Long VALID_NORECORD_ID = 99999l;
  private static final Long INVALID_ID = -1l;

  @Autowired
  private WalletReadServiceImpl SUT;

  @Test
  @Sql("classpath:sql/testFind_empty.sql")
  @Override
  public void testFind_empty() {
    WalletsResponseData walletsResponseData = SUT.findWallets();
    List<WalletData> wallets = walletsResponseData.getWallets();

    assertNotNull(wallets);
    assertTrue(wallets.isEmpty());
  }

  @Test
  @Sql({"classpath:sql/testFind_empty.sql", "classpath:sql/testFindWallets_singleItem.sql"})
  @Override
  public void testFind_single() {
    WalletsResponseData walletsResponseData = SUT.findWallets();
    List<WalletData> wallets = walletsResponseData.getWallets();

    assertNotNull(wallets);
    assertTrue(wallets.size() == 1);
  }
  
  @Test
  @Sql({"classpath:sql/testFind_empty.sql", "classpath:sql/testFindWallets_multipleItems.sql"})
  @Override
  public void testFind_multiple() {
    WalletsResponseData walletsResponseData = SUT.findWallets();
    List<WalletData> wallets = walletsResponseData.getWallets();

    assertNotNull(wallets);
    assertTrue(wallets.size() > 1);
  }

  @Test
  @Override
  public void testFind_validId_recordExist() {
    WalletResponseData walletResponseData = SUT.findWallet(VALID_ID);
    assertNotNull(walletResponseData.getWallet());
  }

  @Test
  @Override
  public void testFind_validId_recordNotExist() {
    assertThrows(WalletNotFoundException.class, () -> SUT.findWallet(VALID_NORECORD_ID));
  }

  @Test
  @Override
  public void testFind_invalidId() {
    assertThrows(WalletNotFoundException.class, () -> SUT.findWallet(INVALID_ID));
  }
}
