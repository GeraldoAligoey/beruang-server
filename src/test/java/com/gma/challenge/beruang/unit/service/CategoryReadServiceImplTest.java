package com.gma.challenge.beruang.unit.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gma.challenge.beruang.common.ReadServiceTest;
import com.gma.challenge.beruang.common.helper.CategoryHelper;
import com.gma.challenge.beruang.data.CategoriesResponseData;
import com.gma.challenge.beruang.data.CategoryResponseData;
import com.gma.challenge.beruang.domain.Category;
import com.gma.challenge.beruang.exception.CategoryNotFoundException;
import com.gma.challenge.beruang.repo.CategoryRepository;
import com.gma.challenge.beruang.service.CategoryReadServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CategoryReadServiceImplTest implements ReadServiceTest {

  private static final Logger LOG = LoggerFactory.getLogger(CategoryReadServiceImplTest.class);
  private static final Long VALID_ID = Long.valueOf(1);
  private static final Long INVALID_ID = Long.valueOf(-1);
  private static final Long VALID_NORECORD_ID = Long.valueOf(100);

  @InjectMocks
  private CategoryReadServiceImpl SUT;

  @Mock
  private CategoryRepository categoryRepository;

  @Test
  @Override
  public void testFind_empty() {
    when(categoryRepository.findAll()).thenReturn(new ArrayList<>());

    CategoriesResponseData responseData = SUT.findCategories(); 
    assertNotNull(responseData);
    assertNotNull(responseData.getCategories());
    assertTrue(responseData.getCategories().isEmpty());
  }

  @Test
  @Override
  public void testFind_single() {
    Category category = CategoryHelper.getCategorySample(false);
    List<Category> categories = new ArrayList<>();
    categories.add(category);

    when(categoryRepository.findAll()).thenReturn(categories);

    CategoriesResponseData responseData = SUT.findCategories(); 
    assertNotNull(responseData);
    assertNotNull(responseData.getCategories());
    assertTrue(responseData.getCategories().size() == 1);
  }

  @Test
  @Override
  public void testFind_multiple() {
    when(categoryRepository.findAll()).thenReturn(CategoryHelper.getCategorySamples(false));

    CategoriesResponseData responseData = SUT.findCategories(); 
    assertNotNull(responseData);
    assertNotNull(responseData.getCategories());
    assertTrue(responseData.getCategories().size() > 1);
    
  }

  @Test
  @Override
  public void testFind_validId_recordExist() {
    Optional<Category> optional = Optional.of(CategoryHelper.getCategorySample(true));
    LOG.info(optional.toString());

    when(categoryRepository.findById(VALID_ID)).thenReturn(optional);

    CategoryResponseData responseData = SUT.findCategory(VALID_ID);
    LOG.info(responseData.toString());

    assertNotNull(responseData.getCategory());
    assertNotNull(responseData.getCategory().getId());
    assertNotNull(responseData.getCategory().getName());
    assertNotNull(responseData.getCategory().getColor());
    assertNotNull(responseData.getCategory().getExpense());
    assertNotNull(responseData.getCategory().getIcon());
    assertNotNull(responseData.getCategory().getActive());
  }

  @Test
  @Override
  public void testFind_validId_recordNotExist() {
    when(categoryRepository.findById(VALID_NORECORD_ID)).thenReturn(Optional.empty());

    assertThrows(CategoryNotFoundException.class, () -> SUT.findCategory(VALID_NORECORD_ID));
  }

  @Test
  @Override
  public void testFind_invalidId() {
    when(categoryRepository.findById(INVALID_ID)).thenReturn(Optional.empty());

    assertThrows(CategoryNotFoundException.class, () -> SUT.findCategory(INVALID_ID));
  }
  
}
