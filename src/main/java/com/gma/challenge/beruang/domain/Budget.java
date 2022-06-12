package com.gma.challenge.beruang.domain;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

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

  @OneToOne(cascade = {
    CascadeType.PERSIST,
    CascadeType.MERGE})
  @JoinColumn
  private Wallet wallet;

  @ManyToMany(cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE })
  private Set<Category> categories = new HashSet<>();

  public void addCategory(Category category) {
    categories.add(category);
  }

  public void removeCategory(Category category) {
    categories.remove(category);
  }
}
