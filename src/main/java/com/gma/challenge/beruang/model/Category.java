package com.gma.challenge.beruang.model;

import javax.persistence.Entity;

import com.gma.challenge.beruang.model.common.CommonNamedClass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Category extends CommonNamedClass {

  private boolean expense;
  private String icon;
  private String color;
  private boolean userDefined;
  private boolean active;

  public Category(String name, boolean expense, String icon, String color, boolean userDefined) {
    this.expense = expense;
    this.icon = icon;
    this.color = color;
    this.userDefined = userDefined;
    this.active = true;
    super.setName(name);
  }

  public Category(String name, boolean expense, String icon, String color, boolean userDefined, boolean active) {
    this.expense = expense;
    this.icon = icon;
    this.color = color;
    this.userDefined = userDefined;
    this.active = active;
    super.setName(name);
  }

}
