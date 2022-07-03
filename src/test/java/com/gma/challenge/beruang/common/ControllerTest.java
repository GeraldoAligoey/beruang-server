package com.gma.challenge.beruang.common;

public interface ControllerTest {

  public void testCreate_validNewRequestData() throws Exception;

  public void testCreate_invalidEmptyNewRequestData() throws Exception;

  public void testCreate_invalidIncompleteNewRequestData() throws Exception;

  public void testCreate_invalidNullNewRequestData() throws Exception;

  public void testUpdate_validId_validFullRequestData() throws Exception;
  
  public void testUpdate_validId_validPartialRequestData() throws Exception;

  public void testUpdate_validId_invalidEmptyRequestData() throws Exception;

  public void testUpdate_validId_invalidNullRequestData() throws Exception;

  public void testUpdate_invalidId_validFullRequestData() throws Exception;
  
  public void testUpdate_invalidId_validPartialRequestData() throws Exception;

  public void testUpdate_invalidId_invalidEmptyRequestData() throws Exception;

  public void testUpdate_invalidId_invalidNullRequestData() throws Exception;

  public void testDelete_validId_linked() throws Exception;

  public void testDelete_validId_unlinked() throws Exception;

  public void testDelete_invalidId() throws Exception;

  public void testFindRecord_validId_recordExist() throws Exception;

  public void testFindRecord_validId_recordNotExist() throws Exception;

  public void testFindRecord_invalidId() throws Exception;

  public void testFindRecords_empty() throws Exception;

  public void testFindRecords_single() throws Exception;

  public void testFindRecords_multiple() throws Exception;
}
