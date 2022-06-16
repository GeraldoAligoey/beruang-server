package com.gma.challenge.beruang.domain;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.gma.challenge.beruang.domain.common.CommonNamedClass;

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
public class Budget extends CommonNamedClass {

  private String period;
  private BigDecimal limitAmount = BigDecimal.ZERO;
  private BigDecimal currentAmount = BigDecimal.ZERO;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn
  private Wallet wallet;

  @ManyToMany(fetch = FetchType.LAZY, cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE,
      CascadeType.DETACH })
  @JoinTable(name = "BUDGET_CATEGORY", joinColumns = @JoinColumn(name = "budget_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
  private Set<Category> categories = new HashSet<>();

  public void addCategory(Category category) {
    categories.add(category);
  }

  public void removeCategory(Category category) {
    categories.remove(category);
  }
}
