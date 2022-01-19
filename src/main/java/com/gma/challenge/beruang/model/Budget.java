package com.gma.challenge.beruang.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

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
public class Budget extends CommonNamedClass {

  private String period;
  private BigDecimal limitAmount;
  private BigDecimal currentAmount = new BigDecimal(0);

  @OneToOne(cascade = {
    CascadeType.PERSIST,
    CascadeType.MERGE})
  @JoinColumn
  private Wallet wallet;

  @ManyToMany(cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE })
  private List<Category> categories = new ArrayList<>();
}
