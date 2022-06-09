package com.gma.challenge.beruang.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import com.gma.challenge.beruang.data.WalletResponseData;
import com.gma.challenge.beruang.exception.IncompleteRequestDataException;
import com.gma.challenge.beruang.exception.InvalidRequestException;
import com.gma.challenge.beruang.exception.WalletNotFoundException;
import com.gma.challenge.beruang.helper.WalletHelper;
import com.gma.challenge.beruang.repo.WalletRepository;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class WalletWriteServiceImplTest {

  private static final Long INVALID_ID = -9999l; 
  private final Long VALID_ID = 1l;

  @Autowired
  private WalletWriteServiceImpl SUT;

  @Autowired
  private WalletRepository walletRepository;

  @Test
  public void testCreateWallet_validRequestData() {
    WalletResponseData responseData = SUT.createWallet(WalletHelper.getValidNewWalletRequestDataSample());
    assertNotNull(responseData);
    assertNotNull(responseData.getWallet());
    assertNotNull(responseData.getWallet().getCategories());
    assertTrue(WalletHelper.isWalletResponseDataEqualsToSample(responseData));
  }

  @Test
  public void testCreateWallet_invalidRequestData() {
    assertThrows(IncompleteRequestDataException.class, () -> SUT.createWallet(WalletHelper.getInvalidNewWalletRequestDataSample()));
  }

  @Test
  public void testUpdateWallet_validId_validFullRequestData() {
    WalletResponseData responseData = SUT.updateWallet(VALID_ID, WalletHelper.getValidFullUpdateRequestDataSample());
    assertNotNull(responseData);
    assertNotNull(responseData.getWallet());
    assertNotNull(responseData.getWallet().getCategories());
    assertTrue(WalletHelper.isWalletUpdatedFullResponseDataEqualsToSample(responseData));
  }

  @Test
  public void testUpdateWallet_validId_validPartialRequestData() {
    WalletResponseData responseData = SUT.updateWallet(VALID_ID, WalletHelper.getValidPartialUpdateRequestDataSample());
    assertNotNull(responseData);
    assertNotNull(responseData.getWallet());
    assertNotNull(responseData.getWallet().getCategories());
    assertTrue(WalletHelper.isWalletUpdatedPartialResponseDataEqualsToSample(responseData));
  }

  @Test
  public void testUpdateWallet_validId_invalidRequestData() {
    assertThrows(InvalidRequestException.class, () -> SUT.updateWallet(VALID_ID, WalletHelper.getInvalidUpdateRequestDataSample()));
  }

  @Test
  public void testUpdateWallet_validId_nullRequestData() {
    assertThrows(InvalidRequestException.class, () -> SUT.updateWallet(VALID_ID, WalletHelper.getNullUpdateRequestDataSample()));
  }

  @Test
  public void testUpdateWallet_invalidId_validFullRequestData() {
    assertThrows(WalletNotFoundException.class, () -> SUT.updateWallet(INVALID_ID, WalletHelper.getValidFullUpdateRequestDataSample()));
  }
  
  @Test
  public void testUpdateWallet_invalidId_validPartialRequestData() {
    assertThrows(WalletNotFoundException.class, () -> SUT.updateWallet(INVALID_ID, WalletHelper.getValidPartialUpdateRequestDataSample()));
  }

  @Test
  public void testUpdateWallet_invalidId_invalidRequestData() {
    assertThrows(InvalidRequestException.class, () -> SUT.updateWallet(INVALID_ID, WalletHelper.getInvalidUpdateRequestDataSample()));
  }

  @Test
  public void testUpdateWallet_invalidId_nullRequestData() {
    assertThrows(InvalidRequestException.class, () -> SUT.updateWallet(INVALID_ID, WalletHelper.getNullUpdateRequestDataSample()));
  }

  @Test
  public void testDeleteWallet_validId() {
    SUT.deleteWallet(VALID_ID);
    assertNull(walletRepository.findById(VALID_ID).orElse(null));
  }

  @Test
  public void testDeleteWallet_invalidId() {
    assertThrows(WalletNotFoundException.class, () -> SUT.deleteWallet(INVALID_ID));
  }
}
