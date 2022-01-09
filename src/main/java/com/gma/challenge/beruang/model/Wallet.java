package com.gma.challenge.beruang.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import com.gma.challenge.beruang.model.common.CommonNamedClass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Wallet extends CommonNamedClass {

  private String defaultCurrencyCode;
  private boolean defaultWallet;
  private BigDecimal balanceAmount;

  @ManyToMany(cascade = { 
    CascadeType.PERSIST, 
    CascadeType.MERGE })
  private List<TransactionCategory> categories = new ArrayList<>();
}
