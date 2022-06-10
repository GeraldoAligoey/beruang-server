package com.gma.challenge.beruang.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import com.gma.challenge.beruang.data.CategoriesResponseData;
import com.gma.challenge.beruang.data.CategoryData;
import com.gma.challenge.beruang.data.CategoryResponseData;
import com.gma.challenge.beruang.exception.CategoryNotFoundException;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class CategoryReadServiceImplTest implements ReadServiceTest {

  private static final long VALID_ID = 1l;
  private static final long VALID_NORECORD_ID = 100l;
  private static final long INVALID_ID = -100l;

  private final static Logger LOG = LoggerFactory.getLogger(CategoryReadServiceImplTest.class);

  @Autowired
  private CategoryReadServiceImpl SUT;

  @Test
  @Sql("classpath:sql/testFind_empty.sql")
  @Override
  public void testFind_empty() {
    CategoriesResponseData categoriesResponseData = SUT.findCategories();
    List<CategoryData> categoryDatas = categoriesResponseData.getCategories();

    LOG.info("categoryDatas size: {}", categoryDatas.size());

    assertNotNull(categoryDatas);
    assertTrue(categoryDatas.isEmpty()); 
  }

  @Test
  @Sql({"classpath:sql/testFind_empty.sql", "classpath:sql/testFindCategories_singleItem.sql"})
  @Override
  public void testFind_single() {
    CategoriesResponseData categoriesResponseData = SUT.findCategories();
    List<CategoryData> categoryDatas = categoriesResponseData.getCategories();

    LOG.info("categoryDatas size: {}", categoryDatas.size());

    assertTrue(categoryDatas.size() == 1); 
  }

  @Test
  @Sql("classpath:sql/testFindCategories_multipleItems.sql")
  @Override
  public void testFind_multiple() {
    CategoriesResponseData categoriesResponseData = SUT.findCategories();
    List<CategoryData> categoryDatas = categoriesResponseData.getCategories();

    LOG.info("categoryDatas size: {}", categoryDatas.size());

    assertTrue(categoryDatas.size() > 1); 
  }

  @Test
  @Sql("classpath:sql/testFindCategories_multipleItems.sql")
  @Override
  public void testFind_validId_recordExist() {
    CategoryResponseData categoryResponseData = SUT.findCategory(VALID_ID);
    assertNotNull(categoryResponseData.getCategory());
  }

  @Test
  @Override
  public void testFind_validId_recordNotExist() {
    assertThrows(CategoryNotFoundException.class, () -> SUT.findCategory(VALID_NORECORD_ID));
  }

  @Test
  @Override
  public void testFind_invalidId() {
    assertThrows(CategoryNotFoundException.class, () -> SUT.findCategory(INVALID_ID));
  }
}
