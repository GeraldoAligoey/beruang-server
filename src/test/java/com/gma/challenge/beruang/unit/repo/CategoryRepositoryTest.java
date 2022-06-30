package com.gma.challenge.beruang.unit.repo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.gma.challenge.beruang.common.RepositoryTest;
import com.gma.challenge.beruang.domain.Category;
import com.gma.challenge.beruang.repo.CategoryRepository;

@DataJpaTest
public class CategoryRepositoryTest implements RepositoryTest {
  
  @Autowired
  private CategoryRepository SUT;

  @Test
  @Override
  public void testFindAll() {
    List<Category> categories = SUT.findAll();
    assertNotNull(categories);
    assertTrue(categories.size() > 1);
    assertNotNull(categories.get(0).getId());
    assertNotNull(categories.get(0).getName());
    assertNotNull(categories.get(0).getIcon());
    assertNotNull(categories.get(0).getColor());
    assertNotNull(categories.get(0).isActive());
    assertNotNull(categories.get(0).isExpense());
  }

  @Test
  @Override
  public void testCreate() {
    Category newCategory = new Category();
    newCategory.setName("test");
    newCategory.setExpense(true);
    newCategory.setIcon("Icon");
    newCategory.setColor("Black");
    newCategory.setUserDefined(true);
    newCategory.setActive(true);

    Category savedCategory = SUT.save(newCategory);

    assertNotNull(savedCategory);
    assertNotNull(savedCategory.getId());
  }

  @Test
  @Override
  public void testUpdate() {
    Category newCategory = new Category();
    newCategory.setName("test");
    newCategory.setExpense(true);
    newCategory.setIcon("Icon");
    newCategory.setColor("Black");
    newCategory.setUserDefined(true);
    newCategory.setActive(true);

    Category savedCategory = SUT.save(newCategory);

    savedCategory.setName("updated name");

    Category updatedCategory = SUT.save(savedCategory);

    assertNotNull(updatedCategory);
    assertTrue(updatedCategory.getName().equals("updated name"));

    Category oldCategory = SUT.findById(Long.valueOf(1)).get();
    oldCategory.setColor("new color");

    Category updatedOldCategory = SUT.save(oldCategory);

    assertNotNull(updatedOldCategory);
    assertTrue(updatedOldCategory.getColor().equals("new color"));
  }

  @Test
  @Override
  public void testDelete() {
    Category newCategory = new Category();
    newCategory.setName("test");
    newCategory.setExpense(true);
    newCategory.setIcon("Icon");
    newCategory.setColor("Black");
    newCategory.setUserDefined(true);
    newCategory.setActive(true);

    Category savedCategory = SUT.save(newCategory);

    SUT.delete(savedCategory);

    assertTrue(!SUT.findById(savedCategory.getId()).isPresent());
  }

}
