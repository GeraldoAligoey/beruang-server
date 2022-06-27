package com.gma.challenge.beruang.integration.service;

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

import com.gma.challenge.beruang.common.ReadServiceTest;
import com.gma.challenge.beruang.data.BudgetData;
import com.gma.challenge.beruang.data.BudgetResponseData;
import com.gma.challenge.beruang.data.BudgetsResponseData;
import com.gma.challenge.beruang.exception.BudgetNotFoundException;
import com.gma.challenge.beruang.exception.WalletNotFoundException;
import com.gma.challenge.beruang.service.BudgetReadServiceImpl;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class BudgetReadServiceImplTest implements ReadServiceTest {

  private static final Long INVALID_BUDGETID = Long.valueOf(-2);
  private static final Long VALID_NORECORD_BUDGETID = Long.valueOf(1000);
  private static final Long VALID_NORECORD_WALLET_ID = Long.valueOf(1000);
  private static final Long VALID_BUDGETID = Long.valueOf(1);
  private static final Long INVALID_WALLET_ID = Long.valueOf(-1);
  private static final Long VALID_WALLET_ID = Long.valueOf(1);

  @Autowired
  BudgetReadServiceImpl SUT;

  @Test
  @Sql("classpath:sql/testFind_empty.sql")
  @Override
  public void testFind_empty() {
    BudgetsResponseData responseData = SUT.findBudgets(VALID_WALLET_ID);
    List<BudgetData> budgets = responseData.getBudgets();

    assertNotNull(budgets);
    assertTrue(budgets.isEmpty());
  }

  @Test
  @Sql("classpath:sql/testFind_empty.sql")
  public void testFind_empty_invalidWalletId() {
    assertThrows(WalletNotFoundException.class, () -> SUT.findBudgets(INVALID_WALLET_ID));
  }

  @Test
  @Sql({"classpath:sql/testFind_empty.sql", "classpath:sql/testFindBudgets_single.sql"})
  @Override
  public void testFind_single() {
    BudgetsResponseData responseData = SUT.findBudgets(VALID_WALLET_ID);
    List<BudgetData> budgets = responseData.getBudgets();

    assertTrue(budgets.size() == 1);
  }

  @Test
  @Sql({"classpath:sql/testFind_empty.sql", "classpath:sql/testFindBudgets_single.sql"})
  public void testFind_single_invalidWalletId() {
    assertThrows(WalletNotFoundException.class, () -> SUT.findBudgets(INVALID_WALLET_ID));
  }

  @Test
  @Sql({"classpath:sql/testFind_empty.sql", "classpath:sql/testFindBudgets_multiple.sql"})
  @Override
  public void testFind_multiple() {
    BudgetsResponseData responseData = SUT.findBudgets(VALID_WALLET_ID);
    List<BudgetData> budgets = responseData.getBudgets();

    assertTrue(budgets.size() > 1);
  }

  @Test
  @Sql({"classpath:sql/testFind_empty.sql", "classpath:sql/testFindBudgets_multiple.sql"})
  public void testFind_multiple_invalidWalletId() {
    assertThrows(WalletNotFoundException.class, () -> SUT.findBudgets(INVALID_WALLET_ID));
  }

  @Test
  @Sql({"classpath:sql/testFind_empty.sql", "classpath:sql/testFindBudgets_multiple.sql"})
  @Override
  public void testFind_validId_recordExist() {
    BudgetResponseData responseData = SUT.findBudget(VALID_WALLET_ID, VALID_BUDGETID);
    assertNotNull(responseData);
    assertNotNull(responseData.getBudget());
  }

  @Test
  @Override
  public void testFind_validId_recordNotExist() {
    assertThrows(BudgetNotFoundException.class, () -> SUT.findBudget(VALID_NORECORD_WALLET_ID, VALID_BUDGETID));
    assertThrows(BudgetNotFoundException.class, () -> SUT.findBudget(VALID_WALLET_ID, VALID_NORECORD_BUDGETID));
  }

  @Test
  @Override
  public void testFind_invalidId() {
    assertThrows(BudgetNotFoundException.class, () -> SUT.findBudget(INVALID_WALLET_ID, VALID_BUDGETID));
    assertThrows(BudgetNotFoundException.class, () -> SUT.findBudget(VALID_WALLET_ID, INVALID_BUDGETID));
  }

}
