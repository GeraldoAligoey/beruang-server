package com.gma.challenge.beruang.integration.controller;

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
import org.springframework.test.context.jdbc.Sql;

import com.gma.challenge.beruang.common.ControllerTest;
import com.gma.challenge.beruang.common.helper.WalletHelper;
import com.gma.challenge.beruang.controller.WalletController;
import com.gma.challenge.beruang.data.WalletResponseData;
import com.gma.challenge.beruang.data.WalletsResponseData;
import com.gma.challenge.beruang.exception.IncompleteRequestDataException;
import com.gma.challenge.beruang.exception.WalletNotFoundException;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class WalletControllerTest implements ControllerTest {

  private static final Long VALID_UNLINKED_ID = Long.valueOf(501);
  private static final Long VALID_LINKED_ID = Long.valueOf(500);
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
  @Sql("classpath:sql/testDeleteWallet.sql")
  public void testDelete_validId_linked() {
    ResponseEntity<Void> responseEntity = SUT.deleteWallet(VALID_LINKED_ID);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertThrows(WalletNotFoundException.class, () -> SUT.findWallet(VALID_LINKED_ID));
  }

  @Test
  @Override
  @Sql("classpath:sql/testDeleteWallet.sql")
  public void testDelete_validId_unlinked() {
    ResponseEntity<Void> responseEntity = SUT.deleteWallet(VALID_UNLINKED_ID);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertThrows(WalletNotFoundException.class, () -> SUT.findWallet(VALID_UNLINKED_ID));
  }

  @Test
  @Override
  public void testDelete_invalidId() {
    assertThrows(WalletNotFoundException.class, () -> SUT.findWallet(INVALID_ID));
  }

  @Test
  @Override
  public void testFindRecord_validId_recordExist() {
    ResponseEntity<WalletResponseData> responseEntity = SUT.findWallet(VALID_ID);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody().getWallet());
  }

  @Test
  @Override
  public void testFindRecord_validId_recordNotExist() {
    assertThrows(WalletNotFoundException.class, () -> SUT.findWallet(10000l));
  }

  @Test
  @Override
  public void testFindRecord_invalidId() {
    assertThrows(WalletNotFoundException.class, () -> SUT.findWallet(INVALID_ID));
  }

  @Test
  @Override
  @Sql("classpath:sql/testFind_empty.sql")
  public void testFindRecords_empty() {
    ResponseEntity<WalletsResponseData> responseEntity = SUT.findWallets();
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody().getWallets());
    assertTrue(responseEntity.getBody().getWallets().isEmpty());
  }

  @Test
  @Override
  @Sql({"classpath:sql/testFind_empty.sql", "classpath:sql/testFindWallets_single.sql"})
  public void testFindRecords_single() {
    ResponseEntity<WalletsResponseData> responseEntity = SUT.findWallets();
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody().getWallets());
    assertTrue(responseEntity.getBody().getWallets().size() == 1);
  }

  @Test
  @Override
  @Sql({"classpath:sql/testFind_empty.sql", "classpath:sql/testFindWallets_multiple.sql"})
  public void testFindRecords_multiple() {
    ResponseEntity<WalletsResponseData> responseEntity = SUT.findWallets();
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody().getWallets());
    assertTrue(responseEntity.getBody().getWallets().size() > 1);
  }

}
