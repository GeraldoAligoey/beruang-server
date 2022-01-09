package com.gma.challenge.beruang.util;

import java.util.List;

public interface DataSampleService<T> {

  public T getSample();

  public List<T> getSamples();
  
}
