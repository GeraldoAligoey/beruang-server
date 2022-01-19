package com.gma.challenge.beruang.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;
import java.util.NoSuchElementException;

import com.gma.challenge.beruang.model.Category;
import com.gma.challenge.beruang.util.CategoryDataSample;

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
public class CategoryRepositoryTest {
  
  @Autowired
  CategoryRepository categoryRepository;

  @BeforeAll
  public void init() {
    categoryRepository.saveAll(new CategoryDataSample().getSamples());
  }

  @Test
  @Order(1)
  public void testFindById() {
    log.info("TransactionCategoryRepositoryTest - testFindById");

    Category category1 = categoryRepository.findById(1l).get();
    assertEquals(1l, category1.getId());
    assertEquals("Food and Drinks", category1.getName());

    Category category2 = categoryRepository.findById(2l).get();
    assertEquals(2l, category2.getId());
    assertEquals("Rental", category2.getName());
  }

  @Test
  @Order(2)
  public void testFindAll() {
    log.info("TransactionCategoryRepositoryTest - testFindAll");

    List<Category> categories = categoryRepository.findAll();
    assertEquals(4, categories.size());
  }

  @Test
  @Order(3)
  public void testDelete() {
    log.info("TransactionCategoryRepositoryTest - testDelete");

    categoryRepository.deleteById(1l);
    Category category = null;

    try {
      category = categoryRepository.findById(1l).get();
    } catch(NoSuchElementException e) {
      log.info(e.getMessage());
    }

    assertNull(category);
  }
}
