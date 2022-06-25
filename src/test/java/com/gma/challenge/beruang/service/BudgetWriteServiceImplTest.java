package com.gma.challenge.beruang.service;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import com.gma.challenge.beruang.data.BudgetResponseData;
import com.gma.challenge.beruang.data.NewBudgetRequestData;
import com.gma.challenge.beruang.exception.BudgetNotFoundException;
import com.gma.challenge.beruang.exception.CategoryNotInWalletException;
import com.gma.challenge.beruang.exception.IncompleteRequestDataException;
import com.gma.challenge.beruang.exception.WalletNotFoundException;
import com.gma.challenge.beruang.helper.BudgetHelper;
import com.gma.challenge.beruang.repo.BudgetRepository;
import com.gma.challenge.beruang.repo.WalletRepository;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class BudgetWriteServiceImplTest implements WriteServiceTest {

  private static final Long INVALID_WALLET_ID = Long.valueOf(-1);
  private static final Long INVALID_BUDGET_ID = Long.valueOf(-1);
  private static final Long VALID_WALLET_ID = Long.valueOf(1);
  private static final Long VALID_BUDGET_ID = Long.valueOf(1);

  @Autowired
  private BudgetWriteServiceImpl SUT;

  @Autowired
  private BudgetRepository budgetRepository;

  @Autowired
  private WalletRepository walletRepository;

  @Test
  @Override
  public void testCreate_validRequestData() {
    BudgetResponseData responseData = SUT.createBudget(VALID_WALLET_ID,
        BudgetHelper.getValidNewBudgetRequestDataSample());
    assertNotNull(responseData);
    assertNotNull(responseData.getBudget());
    assertTrue(BudgetHelper.isBudgetResponseDataEqualsToSample(responseData));
  }

  /**
   * The submitted data contains some invalid category ids, the budget creation will succeed and the invalid category ids will be ignored
   */
  @Test
  public void testCreate_validRequestData_PartialInvalidCategoryId() {
    NewBudgetRequestData requestData = BudgetHelper.getValidNewBudgetRequestDataSample();

    requestData.categoryIds(Arrays.asList(Long.valueOf(1), Long.valueOf(2), Long.valueOf(6)));

    BudgetResponseData responseData = SUT.createBudget(VALID_WALLET_ID, requestData); 
    assertNotNull(responseData);
    assertNotNull(responseData.getBudget());
    assertTrue(BudgetHelper.isBudgetResponseDataEqualsToSample(responseData));
  }

  /**
   * The submitted data doesn't contain valid category ids, an exception will be thrown
   */
  @Test
  public void testCreate_validRequestData_invalidCategoryIds() {
    NewBudgetRequestData requestData = BudgetHelper.getValidNewBudgetRequestDataSample();

    requestData.categoryIds(Arrays.asList(Long.valueOf(5), Long.valueOf(7)));

    assertThrows(CategoryNotInWalletException.class,
        () -> SUT.createBudget(VALID_WALLET_ID, requestData));
  }

  @Test
  @Override
  public void testCreate_invalidIncompleteRequestData() {
    assertThrows(IncompleteRequestDataException.class,
        () -> SUT.createBudget(VALID_WALLET_ID, BudgetHelper.getInvalidIncompleteNewBudgetRequestDataSample()));
  }

  @Test
  @Override
  public void testCreate_invalidEmptyRequestData() {
    assertThrows(IncompleteRequestDataException.class,
        () -> SUT.createBudget(VALID_WALLET_ID, BudgetHelper.getInvalidEmptyNewBudgetRequestDataSample()));
  }

  @Test
  @Override
  public void testCreate_invalidNullRequestData() {
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
    BudgetResponseData responseData = SUT.updateBudget(VALID_WALLET_ID, VALID_BUDGET_ID,
        BudgetHelper.getValidFullUpdateBudgetRequestDataSample());
    assertNotNull(responseData);
    assertNotNull(responseData.getBudget());
    assertTrue(BudgetHelper.isUpdateBudgetResponseDataEqualsToSample(responseData));
  }

  @Test
  @Override
  public void testUpdate_validId_validPartialRequestData() {
    BudgetResponseData responseData = SUT.updateBudget(VALID_WALLET_ID, VALID_BUDGET_ID,
        BudgetHelper.getValidPartialUpdateBudgetRequestDataSample());
    assertNotNull(responseData);
    assertNotNull(responseData.getBudget());
    assertTrue(BudgetHelper.isUpdateBudgetResponseDataEqualsToPartialSample(responseData));
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
  public void testDelete_validId() {
    SUT.deleteBudget(VALID_WALLET_ID, VALID_BUDGET_ID);
    assertNull(budgetRepository.findById(VALID_BUDGET_ID).orElse(null));
    assertNotNull(walletRepository.findById(VALID_WALLET_ID).orElse(null));
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

}
