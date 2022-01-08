package com.gma.challenge.beruang.model;

import javax.persistence.Entity;

import com.gma.challenge.beruang.model.common.CommonNamedClass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Currency extends CommonNamedClass {
 
  private String country;
  private String shortForm;
  private String symbol;
}
