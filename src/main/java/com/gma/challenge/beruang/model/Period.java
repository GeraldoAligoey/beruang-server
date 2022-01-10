package com.gma.challenge.beruang.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Period {
  DAILY(1, "DAILY"),
  WEEKLY(2, "WEEKLY"),
  MONTHLY(3, "MONTHLY"),
  YEARLY(4, "YEARLY")
  ;

  private final int id;
  private final String value;
}
