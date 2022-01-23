package com.gma.challenge.beruang.model;

import javax.persistence.Entity;

import com.gma.challenge.beruang.model.common.CommonNamedClass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Category extends CommonNamedClass {

  private boolean expense;
  private String icon;
  private String color;
  private boolean userDefined;
  private boolean active;
}
