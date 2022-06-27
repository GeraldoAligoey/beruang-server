package com.gma.challenge.beruang.common;

public interface ReadServiceTest {

  public void testFind_empty();

  public void testFind_single();

  public void testFind_multiple();

  public void testFind_validId_recordExist();

  public void testFind_validId_recordNotExist();

  public void testFind_invalidId();
}
