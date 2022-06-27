package com.gma.challenge.beruang.unit.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gma.challenge.beruang.common.ReadServiceTest;
import com.gma.challenge.beruang.data.CategoriesResponseData;
import com.gma.challenge.beruang.repo.CategoryRepository;
import com.gma.challenge.beruang.service.CategoryReadServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CategoryReadServiceImplTest implements ReadServiceTest {

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

  @Override
  public void testFind_single() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void testFind_multiple() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void testFind_validId_recordExist() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void testFind_validId_recordNotExist() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void testFind_invalidId() {
    // TODO Auto-generated method stub
    
  }
  
}
