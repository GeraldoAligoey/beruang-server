package com.gma.challenge.beruang.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

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
public class Wallet extends CommonNamedClass {

  private String defaultCurrencyCode;
  private boolean defaultWallet = false;
  private BigDecimal balanceAmount = new BigDecimal(0);

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
