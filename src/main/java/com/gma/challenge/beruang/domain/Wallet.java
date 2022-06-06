package com.gma.challenge.beruang.domain;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

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
public class Wallet extends CommonNamedClass {

  private String defaultCurrencyCode;
  private boolean defaultWallet = false;
  private BigDecimal initialBalanceAmount = new BigDecimal(0);

  @ManyToMany(fetch = FetchType.EAGER, cascade = { 
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
