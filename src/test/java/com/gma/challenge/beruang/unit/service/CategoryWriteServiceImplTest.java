package com.gma.challenge.beruang.unit.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gma.challenge.beruang.common.WriteServiceTest;
import com.gma.challenge.beruang.common.helper.CategoryHelper;
import com.gma.challenge.beruang.data.CategoryResponseData;
import com.gma.challenge.beruang.domain.Category;
import com.gma.challenge.beruang.exception.CategoryNotFoundException;
import com.gma.challenge.beruang.exception.IncompleteRequestDataException;
import com.gma.challenge.beruang.repo.CategoryRepository;
import com.gma.challenge.beruang.service.CategoryWriteServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CategoryWriteServiceImplTest implements WriteServiceTest {

  private static final Logger LOG = LoggerFactory.getLogger(CategoryReadServiceImplTest.class);
  private static final long VALID_ID = 1l;
  private static final Long INVALID_ID = -1l;

  @InjectMocks
  private CategoryWriteServiceImpl SUT;

  @Mock
  private CategoryRepository categoryRepository;

  @Test
  @Override
  public void testCreate_validRequestData() {
    Category createdCategorySample = CategoryHelper.getCreatedCategorySample();
    when(categoryRepository.saveAndFlush(any())).thenReturn(createdCategorySample);

    CategoryResponseData responseData = SUT.createCategory(CategoryHelper.getValidNewCategoryRequestDataSample());

    LOG.info(responseData.getCategory().toString());

    assertNotNull(responseData); 
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
  public void testCreate_invalidIncompleteRequestData() {
    assertThrows(IncompleteRequestDataException.class,
        () -> SUT.createCategory(CategoryHelper.getInvalidIncompleteNewCategoryRequestDataSample()));
  }

  @Test
  @Override
  public void testCreate_invalidEmptyRequestData() {
    assertThrows(IncompleteRequestDataException.class,
        () -> SUT.createCategory(CategoryHelper.getInvalidEmptyNewCategoryRequestDataSample()));
  }

  @Test
  @Override
  public void testCreate_invalidNullRequestData() {
    assertThrows(IncompleteRequestDataException.class,
        () -> SUT.createCategory(CategoryHelper.getInvalidNullNewCategoryRequestDataSample()));
  }

  @Test
  @Override
  public void testUpdate_validId_validFullRequestData() {
    Category createdCategorySample = CategoryHelper.getCreatedCategorySample();
    
    when(categoryRepository.saveAndFlush(any())).thenReturn(createdCategorySample);
    when(categoryRepository.getReferenceById(VALID_ID)).thenReturn(createdCategorySample);

    CategoryResponseData responseData = SUT.updateCategory(VALID_ID,
        CategoryHelper.getValidFullUpdateCategoryRequestDataSample());

    LOG.info(responseData.getCategory().toString());

    assertNotNull(responseData); 
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
  public void testUpdate_validId_validPartialRequestData() {
    Category createdCategorySample = CategoryHelper.getCreatedCategorySample();
    
    when(categoryRepository.saveAndFlush(any())).thenReturn(createdCategorySample);
    when(categoryRepository.getReferenceById(VALID_ID)).thenReturn(createdCategorySample);

    CategoryResponseData responseData = SUT.updateCategory(VALID_ID,
        CategoryHelper.getValidPartialUpdateCategoryRequestDataSample());

    LOG.info(responseData.getCategory().toString());

    assertNotNull(responseData); 
    assertNotNull(responseData.getCategory()); 
    assertNotNull(responseData.getCategory().getId()); 
    assertNotNull(responseData.getCategory().getName());
    assertNotNull(responseData.getCategory().getColor());
    assertNotNull(responseData.getCategory().getExpense());
    assertNotNull(responseData.getCategory().getIcon());
    assertNotNull(responseData.getCategory().getActive());
    assertTrue(CategoryHelper.isUpdateCategoryResponseDataEqualsToPartialSample(responseData));
  }

  @Test
  @Override
  public void testUpdate_validId_invalidEmptyRequestData() {
    assertThrows(IncompleteRequestDataException.class,
        () -> SUT.updateCategory(VALID_ID, CategoryHelper.getInvalidEmptyUpdateCategoryRequestDataSample()));
  }

  @Test
  @Override
  public void testUpdate_validId_invalidNullRequestData() {
    assertThrows(IncompleteRequestDataException.class,
        () -> SUT.updateCategory(VALID_ID, CategoryHelper.getInvalidNullUpdateCategoryRequestDataSample()));
  }

  @Test
  @Override
  public void testUpdate_invalidId_validFullRequestData() {
    assertThrows(IncompleteRequestDataException.class,
        () -> SUT.updateCategory(VALID_ID, CategoryHelper.getInvalidNullUpdateCategoryRequestDataSample()));
  }

  @Test
  @Override
  public void testUpdate_invalidId_validPartialRequestData() {
    when(categoryRepository.getReferenceById(INVALID_ID)).thenThrow(new EntityNotFoundException());
    assertThrows(CategoryNotFoundException.class,
        () -> SUT.updateCategory(INVALID_ID, CategoryHelper.getValidPartialUpdateCategoryRequestDataSample()));
  }

  @Test
  @Override
  public void testUpdate_invalidId_invalidEmptyRequestData() {
    assertThrows(IncompleteRequestDataException.class,
        () -> SUT.updateCategory(INVALID_ID, CategoryHelper.getInvalidEmptyUpdateCategoryRequestDataSample()));
  }

  @Test
  @Override
  public void testUpdate_invalidId_invalidNullRequestData() {
    assertThrows(IncompleteRequestDataException.class,
        () -> SUT.updateCategory(INVALID_ID, CategoryHelper.getInvalidNullUpdateCategoryRequestDataSample()));
  }

  @Override
  public void testDelete_validId() {

  }

  @Test
  @Override
  public void testDelete_invalidId() {
    when(categoryRepository.findById(INVALID_ID)).thenThrow(new CategoryNotFoundException(""));
    assertThrows(CategoryNotFoundException.class, () -> SUT.deleteCategory(INVALID_ID));
  }
  
}
