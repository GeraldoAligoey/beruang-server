package com.gma.challenge.beruang.integration.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import com.gma.challenge.beruang.common.ControllerTest;
import com.gma.challenge.beruang.common.helper.BudgetHelper;
import com.gma.challenge.beruang.controller.WalletController;
import com.gma.challenge.beruang.data.BudgetData;
import com.gma.challenge.beruang.data.BudgetResponseData;
import com.gma.challenge.beruang.data.BudgetsResponseData;
import com.gma.challenge.beruang.exception.BudgetNotFoundException;
import com.gma.challenge.beruang.exception.IncompleteRequestDataException;
import com.gma.challenge.beruang.exception.WalletNotFoundException;
import com.gma.challenge.beruang.repo.BudgetRepository;
import com.gma.challenge.beruang.repo.WalletRepository;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class WalletControllerBudgetTest implements ControllerTest {

  private static final Long INVALID_BUDGET_ID = Long.valueOf(-1);
  private static final Long VALID_BUDGET_ID = Long.valueOf(1);
  private static final Long INVALID_WALLET_ID = Long.valueOf(-1);
  private static final Long VALID_WALLET_ID = Long.valueOf(1l);

  @Autowired
  private WalletController SUT;

  @Autowired
  private BudgetRepository budgetRepository;

  @Autowired
  private WalletRepository walletRepository;

  @Test
  @Override
  public void testCreate_validNewRequestData() {
    ResponseEntity<BudgetResponseData> responseEntity = SUT.createBudget(VALID_WALLET_ID,
        BudgetHelper.getValidNewBudgetRequestDataSample());
    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody().getBudget());
    assertTrue(BudgetHelper.isBudgetResponseDataEqualsToSample(responseEntity.getBody()));
  }

  @Test
  @Override
  public void testCreate_invalidEmptyNewRequestData() {
    assertThrows(IncompleteRequestDataException.class,
        () -> SUT.createBudget(VALID_WALLET_ID, BudgetHelper.getInvalidEmptyNewBudgetRequestDataSample()));
  }

  @Test
  @Override
  public void testCreate_invalidIncompleteNewRequestData() {
    assertThrows(IncompleteRequestDataException.class,
        () -> SUT.createBudget(VALID_WALLET_ID, BudgetHelper.getInvalidEmptyNewBudgetRequestDataSample()));
  }

  @Test
  @Override
  public void testCreate_invalidNullNewRequestData() {
    assertThrows(IncompleteRequestDataException.class,
        () -> SUT.createBudget(VALID_WALLET_ID, BudgetHelper.getInvalidNullNewBudgetRequestDataSample()));
  }

  @Test
  public void testCreate_invalidWalletId_validRequestData() {
    assertThrows(WalletNotFoundException.class,
        () -> SUT.createBudget(INVALID_WALLET_ID, BudgetHelper.getValidNewBudgetRequestDataSample()));
  }

  @Test
  public void testCreate_invalidWalletId_invalidIncompleteRequestData() {
    assertThrows(IncompleteRequestDataException.class,
        () -> SUT.createBudget(INVALID_WALLET_ID, BudgetHelper.getInvalidIncompleteNewBudgetRequestDataSample()));
  }

  @Test
  public void testCreate_invalidWalletId_invalidEmptyRequestData() {
    assertThrows(IncompleteRequestDataException.class,
        () -> SUT.createBudget(INVALID_WALLET_ID, BudgetHelper.getInvalidEmptyNewBudgetRequestDataSample()));
  }

  @Test
  public void testCreate_invalidWalletId_invalidNullRequestData() {
    assertThrows(IncompleteRequestDataException.class,
        () -> SUT.createBudget(INVALID_WALLET_ID, BudgetHelper.getInvalidNullNewBudgetRequestDataSample()));
  }

  @Test
  @Override
  public void testUpdate_validId_validFullRequestData() {
    ResponseEntity<BudgetResponseData> responseEntity = SUT.updateBudget(VALID_WALLET_ID, VALID_BUDGET_ID,
        BudgetHelper.getValidFullUpdateBudgetRequestDataSample());
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody().getBudget());
    assertTrue(BudgetHelper.isUpdateBudgetResponseDataEqualsToSample(responseEntity.getBody()));
  }

  @Test
  @Override
  public void testUpdate_validId_validPartialRequestData() {
    ResponseEntity<BudgetResponseData> responseEntity = SUT.updateBudget(VALID_WALLET_ID, VALID_BUDGET_ID,
        BudgetHelper.getValidPartialUpdateBudgetRequestDataSample());
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody().getBudget());
    assertTrue(BudgetHelper.isUpdateBudgetResponseDataEqualsToPartialSample(responseEntity.getBody()));
  }

  @Test
  @Override
  public void testUpdate_validId_invalidEmptyRequestData() {
    assertThrows(IncompleteRequestDataException.class,
        () -> SUT.updateBudget(VALID_WALLET_ID, VALID_BUDGET_ID,
            BudgetHelper.getInvalidEmptyUpdateBudgetRequestDataSample()));
  }

  @Test
  @Override
  public void testUpdate_validId_invalidNullRequestData() {
    assertThrows(IncompleteRequestDataException.class,
        () -> SUT.updateBudget(VALID_WALLET_ID, VALID_BUDGET_ID,
            BudgetHelper.getInvalidNullUpdateBudgetRequestDataSample()));
  }

  @Test
  @Override
  public void testUpdate_invalidId_validFullRequestData() {
    assertThrows(WalletNotFoundException.class,
        () -> SUT.updateBudget(INVALID_WALLET_ID, VALID_BUDGET_ID,
            BudgetHelper.getValidFullUpdateBudgetRequestDataSample()));

    assertThrows(BudgetNotFoundException.class,
        () -> SUT.updateBudget(VALID_WALLET_ID, INVALID_BUDGET_ID,
            BudgetHelper.getValidFullUpdateBudgetRequestDataSample()));

    assertThrows(BudgetNotFoundException.class,
        () -> SUT.updateBudget(INVALID_WALLET_ID, INVALID_BUDGET_ID,
            BudgetHelper.getValidFullUpdateBudgetRequestDataSample()));
  }

  @Test
  @Override
  public void testUpdate_invalidId_validPartialRequestData() {
    assertThrows(WalletNotFoundException.class,
        () -> SUT.updateBudget(INVALID_WALLET_ID, VALID_BUDGET_ID,
            BudgetHelper.getValidPartialUpdateBudgetRequestDataSample()));

    assertThrows(BudgetNotFoundException.class,
        () -> SUT.updateBudget(VALID_WALLET_ID, INVALID_BUDGET_ID,
            BudgetHelper.getValidPartialUpdateBudgetRequestDataSample()));

    assertThrows(BudgetNotFoundException.class,
        () -> SUT.updateBudget(INVALID_WALLET_ID, INVALID_BUDGET_ID,
            BudgetHelper.getValidPartialUpdateBudgetRequestDataSample()));
  }

  @Test
  @Override
  public void testUpdate_invalidId_invalidEmptyRequestData() {
    assertThrows(IncompleteRequestDataException.class,
        () -> SUT.updateBudget(INVALID_WALLET_ID, VALID_BUDGET_ID,
            BudgetHelper.getInvalidEmptyUpdateBudgetRequestDataSample()));

    assertThrows(IncompleteRequestDataException.class,
        () -> SUT.updateBudget(VALID_WALLET_ID, INVALID_BUDGET_ID,
            BudgetHelper.getInvalidEmptyUpdateBudgetRequestDataSample()));

    assertThrows(IncompleteRequestDataException.class,
        () -> SUT.updateBudget(INVALID_WALLET_ID, INVALID_BUDGET_ID,
            BudgetHelper.getInvalidEmptyUpdateBudgetRequestDataSample()));
  }

  @Test
  @Override
  public void testUpdate_invalidId_invalidNullRequestData() {
    assertThrows(IncompleteRequestDataException.class,
        () -> SUT.updateBudget(INVALID_WALLET_ID, VALID_BUDGET_ID,
            BudgetHelper.getInvalidNullUpdateBudgetRequestDataSample()));

    assertThrows(IncompleteRequestDataException.class,
        () -> SUT.updateBudget(VALID_WALLET_ID, INVALID_BUDGET_ID,
            BudgetHelper.getInvalidNullUpdateBudgetRequestDataSample()));

    assertThrows(IncompleteRequestDataException.class,
        () -> SUT.updateBudget(INVALID_WALLET_ID, INVALID_BUDGET_ID,
            BudgetHelper.getInvalidNullUpdateBudgetRequestDataSample()));
  }

  @Test
  @Override
  public void testDelete_validId_linked() {
    SUT.deleteBudget(VALID_WALLET_ID, VALID_BUDGET_ID);
    assertNull(budgetRepository.findById(VALID_BUDGET_ID).orElse(null));
    assertNotNull(walletRepository.findById(VALID_WALLET_ID).orElse(null));
  }

  @Override
  public void testDelete_validId_unlinked() {
    // Budget cannot be unlinked
  }

  @Test
  @Override
  public void testDelete_invalidId() {
    assertThrows(BudgetNotFoundException.class,
        () -> SUT.deleteBudget(INVALID_WALLET_ID, INVALID_BUDGET_ID));

    assertThrows(WalletNotFoundException.class,
        () -> SUT.deleteBudget(INVALID_WALLET_ID, VALID_BUDGET_ID));

    assertThrows(BudgetNotFoundException.class,
        () -> SUT.deleteBudget(VALID_WALLET_ID, INVALID_BUDGET_ID));
  }

  @Test
  @Sql({ "classpath:sql/testFind_empty.sql", "classpath:sql/testFindBudgets_multiple.sql" })
  @Override
  public void testFindRecord_validId_recordExist() {
    ResponseEntity<BudgetResponseData> responseEntity = SUT.findBudget(VALID_WALLET_ID, VALID_BUDGET_ID);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody().getBudget());
  }

  @Test
  @Override
  public void testFindRecord_validId_recordNotExist() {
    assertThrows(BudgetNotFoundException.class, () -> SUT.findBudget(10000l, 10000l));
    assertThrows(BudgetNotFoundException.class, () -> SUT.findBudget(VALID_WALLET_ID, 10000l));
    assertThrows(BudgetNotFoundException.class, () -> SUT.findBudget(10000l, VALID_BUDGET_ID));
  }

  @Test
  @Override
  public void testFindRecord_invalidId() {
    assertThrows(BudgetNotFoundException.class, () -> SUT.findBudget(INVALID_WALLET_ID, VALID_BUDGET_ID));
    assertThrows(BudgetNotFoundException.class, () -> SUT.findBudget(VALID_WALLET_ID, INVALID_BUDGET_ID));
  }

  @Test
  @Sql("classpath:sql/testFind_empty.sql")
  @Override
  public void testFindRecords_empty() {
    ResponseEntity<BudgetsResponseData> responseEntity = SUT.findBudgets(VALID_WALLET_ID);
    BudgetsResponseData responseData = responseEntity.getBody();

    assertNotNull(responseData);
    assertTrue(responseData.getBudgets().isEmpty());
  }

  @Test
  @Sql({ "classpath:sql/testFind_empty.sql", "classpath:sql/testFindBudgets_single.sql" })
  @Override
  public void testFindRecords_single() {
    ResponseEntity<BudgetsResponseData> responseEntity = SUT.findBudgets(VALID_WALLET_ID);
    List<BudgetData> budgets = responseEntity.getBody().getBudgets();

    assertTrue(budgets.size() == 1);
  }

  @Test
  @Sql({ "classpath:sql/testFind_empty.sql", "classpath:sql/testFindBudgets_multiple.sql" })
  @Override
  public void testFindRecords_multiple() {
    ResponseEntity<BudgetsResponseData> responseEntity = SUT.findBudgets(VALID_WALLET_ID);
    List<BudgetData> budgets = responseEntity.getBody().getBudgets();

    assertTrue(budgets.size() > 1);
  }

}
