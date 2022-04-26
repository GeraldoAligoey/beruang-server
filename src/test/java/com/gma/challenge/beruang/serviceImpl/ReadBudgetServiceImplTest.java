package com.gma.challenge.beruang.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.gma.challenge.beruang.model.Budget;
import com.gma.challenge.beruang.repo.BudgetRepository;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Sql("classpath:test_budget.sql")
public class ReadBudgetServiceImplTest {

  private static final String DAILY = "DAILY";
  private static final String MONTHLY = "MONTHLY";
  private static final Logger LOG = LoggerFactory.getLogger(ReadBudgetServiceImplTest.class);

  @Autowired
  private BudgetRepository budgetRepository;

  @Test
  public void findAll() {
    List<Budget> budgets = budgetRepository.findAll();

    for (Budget budget : budgets) {
      LOG.info("Budget id: {}; name: {}; period: {}", budget.getId(), budget.getName(), budget.getPeriod());
    }

    assertTrue(budgets.size() == 3);
  }
  
  @Test
  public void findAllByWalletId() {
    List<Budget> budgets = budgetRepository.findAllByWalletId(1l);

    for (Budget budget : budgets) {
      LOG.info("Budget id: {}; name: {}; period: {}", budget.getId(), budget.getName(), budget.getPeriod());
    }

    assertTrue(budgets.size() == 2);

    budgets = budgetRepository.findAllByWalletId(2l);

    for (Budget budget : budgets) {
      LOG.info("Budget id: {}; name: {}; period: {}", budget.getId(), budget.getName(), budget.getPeriod());
    }

    assertTrue(budgets.size() == 1);
  }

  @Test
  public void findAllByPeriodId() {
    List<Budget> budgets = budgetRepository.findAllByPeriod(MONTHLY);

    for (Budget budget : budgets) {
      LOG.info("Budget id: {}; name: {}; period: {}", budget.getId(), budget.getName(), budget.getPeriod());
    }

    assertTrue(budgets.size() == 2);

    budgets = budgetRepository.findAllByPeriod(DAILY);

    for (Budget budget : budgets) {
      LOG.info("Budget id: {}; name: {}; period: {}", budget.getId(), budget.getName(), budget.getPeriod());
    }

    assertTrue(budgets.size() == 1);

  }
}
