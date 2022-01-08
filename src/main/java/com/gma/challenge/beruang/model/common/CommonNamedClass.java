package com.gma.challenge.beruang.model.common;

import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class CommonNamedClass extends CommonClass {
  
  private String name;

}
