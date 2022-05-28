package com.gma.challenge.beruang.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum Period {
  DAILY(1, "DAILY"),
  WEEKLY(2, "WEEKLY"),
  MONTHLY(3, "MONTHLY"),
  YEARLY(4, "YEARLY")
  ;

  private final int id;
  private final String value;
}
