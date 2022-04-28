package com.gma.challenge.beruang.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.gma.challenge.beruang.model.Category;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Sql("classpath:test_category.sql")
public class CategoryRepositoryTest {

  private final static Logger LOG = LoggerFactory.getLogger(CategoryRepositoryTest.class);

  @Autowired
  CategoryRepository categoryRepository;

  @Test
  public void findAll() {
    List<Category> categories = categoryRepository.findAll();
    LOG.info("Categories size: {}", categories.size());

    for (Category category : categories) {
      LOG.info("Category name: {}", category.getName());
    }

    assertTrue(categories.size() == 7);
  }

  @Test
  public void findByWalletId() {
    Set<Category> categories = categoryRepository.findByWalletId(2l);

    assertTrue(categories.size() == 2);

    for (Category category : categories) {
      LOG.info("Category name: {}", category.getName());
    }

    Iterator<Category> iterator = categories.iterator();

    assertEquals("part time", iterator.next().getName());
    assertEquals("salary", iterator.next().getName());
  }

  @Test
  public void findByBudgetId() {
    Set<Category> categories = categoryRepository.findByBudgetId(1l);

    assertTrue(categories.size() == 2);

    for (Category category : categories) {
      LOG.info("Category name: {}", category.getName());
    }
    
    Iterator<Category> iterator = categories.iterator();

    assertEquals("food and drinks", iterator.next().getName());
    assertEquals("transport", iterator.next().getName());
  }
}
