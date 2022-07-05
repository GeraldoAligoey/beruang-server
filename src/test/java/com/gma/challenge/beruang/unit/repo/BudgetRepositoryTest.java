package com.gma.challenge.beruang.unit.repo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.gma.challenge.beruang.common.RepositoryTest;
import com.gma.challenge.beruang.domain.Budget;
import com.gma.challenge.beruang.domain.Category;
import com.gma.challenge.beruang.domain.Period;
import com.gma.challenge.beruang.domain.Wallet;
import com.gma.challenge.beruang.repo.BudgetRepository;

@DataJpaTest
public class BudgetRepositoryTest implements RepositoryTest {

  @Autowired
  BudgetRepository SUT;

  @Test
  @Override
  public void testFindAll() {
    List<Budget> budgets = SUT.findAll(); 
    assertNotNull(budgets);
    assertTrue(budgets.size() > 1);
    assertNotNull(budgets.get(0).getId());
    assertNotNull(budgets.get(0).getName());
    assertNotNull(budgets.get(0).getLimitAmount());
    assertNotNull(budgets.get(0).getPeriod());
    assertNotNull(budgets.get(0).getCurrentAmount());
    assertNotNull(budgets.get(0).getWallet());
    assertNotNull(budgets.get(0).getCategories());
  }

  @Test
  @Override
  public void testCreate() {
    Budget newBudget = new Budget(); 
    newBudget.setName("test");
    newBudget.setPeriod("weekly");
    newBudget.setLimitAmount(new BigDecimal(1500));
    newBudget.setCurrentAmount(new BigDecimal(1000));

    Wallet wallet = new Wallet();
    wallet.setId(Long.valueOf(1));

    newBudget.setWallet(wallet);

    Set<Category> categories = new HashSet<>();
    Category c1 = new Category();
    c1.setId(Long.valueOf(1));
    categories.add(c1);

    Category c2 = new Category();
    c2.setId(Long.valueOf(2));
    categories.add(c2);

    newBudget.addCategory(c1);
    newBudget.addCategory(c2);

    Budget savedBudget = SUT.save(newBudget);
    
    assertNotNull(savedBudget);
    assertNotNull(savedBudget.getId());
  }

  @Test
  @Override
  public void testUpdate() {
    Budget newBudget = new Budget(); 
    newBudget.setName("test");
    newBudget.setPeriod("weekly");
    newBudget.setLimitAmount(new BigDecimal(1500));
    newBudget.setCurrentAmount(new BigDecimal(1000));

    Wallet wallet = new Wallet();
    wallet.setId(Long.valueOf(1));

    newBudget.setWallet(wallet);

    Set<Category> categories = new HashSet<>();
    Category c1 = new Category();
    c1.setId(Long.valueOf(1));
    categories.add(c1);

    Category c2 = new Category();
    c2.setId(Long.valueOf(2));
    categories.add(c2);

    newBudget.addCategory(c1);
    newBudget.addCategory(c2);

    Budget savedBudget = SUT.save(newBudget);
   
    savedBudget.setName("updated budget name");
  
    Budget updatedBudget = SUT.save(savedBudget);

    assertNotNull(updatedBudget);
    assertTrue(updatedBudget.getName().equals("updated budget name"));

    Budget oldBudget = SUT.findById(Long.valueOf(1)).get();

    oldBudget.setPeriod(Period.MONTHLY.getValue());

    Budget updatedOldBudget = SUT.save(oldBudget);
    
    assertNotNull(updatedOldBudget);
    assertTrue(updatedOldBudget.getPeriod().equals(Period.MONTHLY.getValue()));
  }

  @Test
  @Override
  public void testDelete() {
    Budget budget = SUT.findById(Long.valueOf(1)).get();
    SUT.delete(budget); 

    assertTrue(!SUT.findById(Long.valueOf(1)).isPresent());
  }
  
}
