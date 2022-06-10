package com.gma.challenge.beruang.controller;

public interface ControllerTest {

  public void testCreate_validNewRequestData();

  public void testCreate_invalidEmptyNewRequestData();

  public void testCreate_invalidIncompleteNewRequestData();

  public void testCreate_invalidNullNewRequestData();

  public void testUpdate_validId_validFullRequestData();
  
  public void testUpdate_validId_validPartialRequestData();

  public void testUpdate_validId_invalidEmptyRequestData();

  public void testUpdate_validId_invalidNullRequestData();

  public void testUpdate_invalidId_validFullRequestData();
  
  public void testUpdate_invalidId_validPartialRequestData();

  public void testUpdate_invalidId_invalidEmptyRequestData();

  public void testUpdate_invalidId_invalidNullRequestData();

  public void testDelete_validId_linked();

  public void testDelete_validId_unlinked();

  public void testDelete_invalidId();

  public void testFindRecord_validId_recordExist();

  public void testFindRecord_validId_recordNotExist();

  public void testFindRecord_invalidId();

  public void testFindRecords_empty();

  public void testFindRecords_single();

  public void testFindRecords_multiple();
}
