package com.gma.challenge.beruang.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;
import java.util.NoSuchElementException;

import com.gma.challenge.beruang.model.Budget;
import com.gma.challenge.beruang.model.Period;
import com.gma.challenge.beruang.util.BudgetDataSample;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import lombok.extern.slf4j.Slf4j;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
@Slf4j
public class BudgetRepositoryTest {
  
  @Autowired
  BudgetRepository budgetRepository;

  @BeforeAll
  public void init() {
    budgetRepository.saveAll(new BudgetDataSample().getSamples());
  }

  @Test
  @Order(1)
  public void testFindById() {
    log.info("BudgetRepositoryTest - testFindById");

    Budget budget1 = budgetRepository.findById(1l).get();
    assertEquals("Monthly Food Budget", budget1.getName());
    assertEquals(Period.MONTHLY.getValue(), budget1.getPeriod());

    Budget budget2 = budgetRepository.findById(2l).get();
    assertEquals("Monthly Spending Budget", budget2.getName());
    assertEquals(Period.MONTHLY.getValue(), budget2.getPeriod());
  }
  
  @Test
  @Order(2)
  public void testFindAll() {
    log.info("BudgetRepositoryTest - testFindAll");

    List<Budget> budgets = budgetRepository.findAll();
    assertEquals(2, budgets.size());
  }

  @Test
  @Order(3)
  public void testDelete() {
    log.info("BudgetRepositoryTest - testDelete");

    budgetRepository.deleteById(1l);
    Budget budget = null;

    try {
      budget = budgetRepository.findById(1l).get();
    } catch(NoSuchElementException e) {
      log.info(e.getMessage());
    }

    assertNull(budget);
  }
}
