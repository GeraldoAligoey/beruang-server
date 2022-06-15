package com.gma.challenge.beruang.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import com.gma.challenge.beruang.data.BudgetResponseData;
import com.gma.challenge.beruang.helper.BudgetHelper;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class BudgetWriteServiceImplTest implements WriteServiceTest {

  @Autowired
  private BudgetWriteServiceImpl SUT;

  @Test
  @Override
  public void testCreate_validRequestData() {
    BudgetResponseData responseData = SUT.createBudget(Long.valueOf(1), BudgetHelper.getValidNewBudgetRequestDataSample());
    assertNotNull(responseData);
    assertNotNull(responseData.getBudget());
    assertTrue(BudgetHelper.isBudgetResponseDataEqualsToSample(responseData));
  }

  @Override
  public void testCreate_invalidIncompleteRequestData() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void testCreate_invalidEmptyRequestData() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void testCreate_invalidNullRequestData() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void testUpdate_validId_validFullRequestData() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void testUpdate_validId_validPartialRequestData() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void testUpdate_validId_invalidEmptyRequestData() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void testUpdate_validId_invalidNullRequestData() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void testUpdate_invalidId_validFullRequestData() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void testUpdate_invalidId_validPartialRequestData() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void testUpdate_invalidId_invalidEmptyRequestData() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void testUpdate_invalidId_invalidNullRequestData() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void testDelete_validId() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void testDelete_invalidId() {
    // TODO Auto-generated method stub
    
  }
  
}
