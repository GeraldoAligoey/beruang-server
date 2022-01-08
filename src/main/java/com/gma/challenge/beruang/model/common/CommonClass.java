package com.gma.challenge.beruang.model.common;

import java.math.BigInteger;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class CommonClass {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private BigInteger id;
  
}
