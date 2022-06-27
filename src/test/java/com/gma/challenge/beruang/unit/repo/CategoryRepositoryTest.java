package com.gma.challenge.beruang.unit.repo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.gma.challenge.beruang.domain.Category;
import com.gma.challenge.beruang.repo.CategoryRepository;

@DataJpaTest
public class CategoryRepositoryTest {
  
  @Autowired
  private CategoryRepository SUT;

  @Test
  public void testFindAll() {
    List<Category> categories = SUT.findAll();
    assertNotNull(categories);
    assertTrue(categories.size() > 1);
  }

}
