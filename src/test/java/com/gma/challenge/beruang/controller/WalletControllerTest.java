package com.gma.challenge.beruang.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import com.gma.challenge.beruang.data.WalletResponseData;
import com.gma.challenge.beruang.exception.IncompleteRequestDataException;
import com.gma.challenge.beruang.exception.WalletNotFoundException;
import com.gma.challenge.beruang.helper.WalletHelper;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class WalletControllerTest implements ControllerTest {

  private static final Long INVALID_ID = Long.valueOf(-10000);
  private static final Long VALID_ID = Long.valueOf(1);

  @Autowired
  WalletController SUT;

  @Test
  @Override
  public void testCreate_validNewRequestData() {
    ResponseEntity<WalletResponseData> responseEntity = SUT
        .createWallet(WalletHelper.getValidNewWalletRequestDataSample());
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody().getWallet());
    assertTrue(WalletHelper.isWalletResponseDataEqualsToSample(responseEntity.getBody()));
  }

  @Test
  @Override
  public void testCreate_invalidEmptyNewRequestData() {
    assertThrows(IncompleteRequestDataException.class,
        () -> SUT.createWallet(WalletHelper.getInvalidEmptyNewWalletRequestDataSample()));
  }

  @Test
  @Override
  public void testCreate_invalidIncompleteNewRequestData() {
    assertThrows(IncompleteRequestDataException.class,
        () -> SUT.createWallet(WalletHelper.getInvalidIncompleteNewWalletRequestDataSample()));
  }

  @Test
  @Override
  public void testCreate_invalidNullNewRequestData() {
    assertThrows(IncompleteRequestDataException.class,
        () -> SUT.createWallet(WalletHelper.getInvalidNullNewWalletRequestDataSample()));
  }

  @Test
  @Override
  public void testUpdate_validId_validFullRequestData() {
    ResponseEntity<WalletResponseData> responseEntity = SUT.updateWallet(VALID_ID,
        WalletHelper.getValidFullUpdateRequestDataSample());
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody().getWallet());
    assertTrue(WalletHelper.isWalletUpdatedFullResponseDataEqualsToSample(responseEntity.getBody()));
  }

  @Test
  @Override
  public void testUpdate_validId_validPartialRequestData() {
    ResponseEntity<WalletResponseData> responseEntity = SUT.updateWallet(VALID_ID,
        WalletHelper.getValidPartialUpdateRequestDataSample());
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody().getWallet());
    assertTrue(WalletHelper.isWalletUpdatedPartialResponseDataEqualsToSample(responseEntity.getBody()));
  }

  @Test
  @Override
  public void testUpdate_validId_invalidEmptyRequestData() {
    assertThrows(IncompleteRequestDataException.class,
        () -> SUT.updateWallet(VALID_ID, WalletHelper.getInvalidEmptyUpdateRequestDataSample()));
  }

  @Test
  @Override
  public void testUpdate_validId_invalidNullRequestData() {
    assertThrows(IncompleteRequestDataException.class,
        () -> SUT.updateWallet(VALID_ID, WalletHelper.getInvalidNullUpdateRequestDataSample()));
  }

  @Test
  @Override
  public void testUpdate_invalidId_validFullRequestData() {
    assertThrows(WalletNotFoundException.class,
        () -> SUT.updateWallet(INVALID_ID, WalletHelper.getValidFullUpdateRequestDataSample()));
  }

  @Test
  @Override
  public void testUpdate_invalidId_validPartialRequestData() {
    assertThrows(WalletNotFoundException.class,
        () -> SUT.updateWallet(INVALID_ID, WalletHelper.getValidPartialUpdateRequestDataSample()));
  }

  @Test
  @Override
  public void testUpdate_invalidId_invalidEmptyRequestData() {
    assertThrows(IncompleteRequestDataException.class,
        () -> SUT.updateWallet(INVALID_ID, WalletHelper.getInvalidEmptyUpdateRequestDataSample()));
  }

  @Test
  @Override
  public void testUpdate_invalidId_invalidNullRequestData() {
    assertThrows(IncompleteRequestDataException.class,
        () -> SUT.updateWallet(INVALID_ID, WalletHelper.getInvalidNullUpdateRequestDataSample()));
  }

  @Test
  @Override
  public void testDelete_validId_linked() {
    // TODO Auto-generated method stub

  }

  @Test
  @Override
  public void testDelete_validId_unlinked() {
    // TODO Auto-generated method stub

  }

  @Test
  @Override
  public void testDelete_invalidId() {
    // TODO Auto-generated method stub

  }

  @Test
  @Override
  public void testFindRecord_validId_recordExist() {
    // TODO Auto-generated method stub

  }

  @Test
  @Override
  public void testFindRecord_validId_recordNotExist() {
    // TODO Auto-generated method stub

  }

  @Test
  @Override
  public void testFindRecord_invalidId() {
    // TODO Auto-generated method stub

  }

  @Test
  @Override
  public void testFindRecords_empty() {
    // TODO Auto-generated method stub

  }

  @Test
  @Override
  public void testFindRecords_single() {
    // TODO Auto-generated method stub

  }

  @Test
  @Override
  public void testFindRecords_multiple() {
    // TODO Auto-generated method stub

  }

}
