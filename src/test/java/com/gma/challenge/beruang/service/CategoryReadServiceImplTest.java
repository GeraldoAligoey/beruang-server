package com.gma.challenge.beruang.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.gma.challenge.beruang.data.CategoriesResponseData;
import com.gma.challenge.beruang.data.CategoryData;
import com.gma.challenge.beruang.data.CategoryResponseData;
import com.gma.challenge.beruang.exception.CategoryNotFoundException;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@ActiveProfiles("test")
public class CategoryReadServiceImplTest {
  
  private final static Logger LOG = LoggerFactory.getLogger(CategoryReadServiceImplTest.class);

  @Autowired
  private CategoryReadServiceImpl SUT;

  @Test
  @Sql("classpath:sql/testGetCategories_empty.sql")
  public void testGetCategories_empty() {
    CategoriesResponseData categoriesResponseData = SUT.getCategories();
    List<CategoryData> categoryDatas = categoriesResponseData.getCategories();

    LOG.info("categoryDatas size: {}", categoryDatas.size());

    assertNotNull(categoryDatas);
    assertTrue(categoryDatas.isEmpty()); 
  }

  @Test
  @Sql("classpath:sql/testGetCategories_singleItem.sql")
  public void testGetCategories_singleItem() {
    CategoriesResponseData categoriesResponseData = SUT.getCategories();
    List<CategoryData> categoryDatas = categoriesResponseData.getCategories();

    LOG.info("categoryDatas size: {}", categoryDatas.size());

    assertTrue(categoryDatas.size() == 1); 
  }

  @Test
  @Sql("classpath:sql/testGetCategories_multipleItems.sql")
  public void testGetCategories_multipleItems() {
    CategoriesResponseData categoriesResponseData = SUT.getCategories();
    List<CategoryData> categoryDatas = categoriesResponseData.getCategories();

    LOG.info("categoryDatas size: {}", categoryDatas.size());

    assertTrue(categoryDatas.size() > 1); 
  }

  @Test
  @Sql("classpath:sql/testGetCategories_multipleItems.sql")
  public void testFindCategory_validIdRecordExist() {
    CategoryResponseData categoryResponseData = SUT.findCategory(1l);
    CategoryData categoryData = categoryResponseData.getCategory();

    assertTrue(categoryData != null);
  }

  @Test
  public void testFindCategory_validIdRecordNotExist() {
    assertThrows(CategoryNotFoundException.class, () -> SUT.findCategory(100l));
  }

  @Test
  public void testFindCategory_invalidId() {
    assertThrows(CategoryNotFoundException.class, () -> SUT.findCategory(-100l));
  }
}
