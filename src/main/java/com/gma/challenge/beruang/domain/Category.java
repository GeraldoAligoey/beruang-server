package com.gma.challenge.beruang.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import com.gma.challenge.beruang.domain.common.CommonNamedClass;

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

  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "categories")
  @Column(name = "wallet_id")
  Set<Wallet> wallets = new HashSet<>();  

  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "categories")
  @Column(name = "budget_id")
  Set<Budget> budgets = new HashSet<>();  

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

  // public void addWallet(Wallet wallet) {
  //   wallets.add(wallet);
  // }

  // public void removeWallet(Wallet wallet) {
  //   wallets.remove(wallet);
  // }

  // public void addBudget(Budget budget) {
  //   budgets.add(budget);
  // }

  // public void removeBudget(Budget budget) {
  //   budgets.remove(budget);
  // }
}
