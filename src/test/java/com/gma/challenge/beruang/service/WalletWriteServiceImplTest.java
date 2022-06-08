package com.gma.challenge.beruang.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import com.gma.challenge.beruang.data.WalletResponseData;
import com.gma.challenge.beruang.helper.WalletHelper;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class WalletWriteServiceImplTest {

  @Autowired
  private WalletWriteServiceImpl SUT;

  @Test
  public void testCreateWallet_validRequestData() {
    WalletResponseData responseData = SUT.createWallet(WalletHelper.getValidNewWalletRequestDataSample());
    assertNotNull(responseData);
    assertNotNull(responseData.getWallet());
    assertNotNull(responseData.getWallet().getCategories());
    assertTrue(WalletHelper.isWalletResponseDataEqualsToSample(responseData));
  }

  public void testCreateWallet_invalidRequestData() {
    
  }

  public void testUpdateWallet_validId_validFullRequestData() {

  }

  public void testUpdateWallet_validId_validPartialRequestData() {

  }

  public void testUpdateWallet_validId_invalidRequestData() {

  }

  public void testUpdateWallet_invalidId_validFullRequestData() {

  }
  
  public void testUpdateWallet_invalidId_validPartialRequestData() {

  }

  public void testUpdateWallet_invalidId_validRequestData() {

  }

  public void testDeleteWallet_validId() {

  }

  public void testDeleteWallet_invalidId() {

  }
}
