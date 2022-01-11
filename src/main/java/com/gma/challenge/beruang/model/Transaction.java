package com.gma.challenge.beruang.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.gma.challenge.beruang.model.common.CommonClass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction extends CommonClass {
  
  private String note;
  private BigDecimal amount;
  private Date date;

  @OneToOne(cascade = {
    CascadeType.PERSIST,
    CascadeType.MERGE
  })
  @JoinColumn
  private Wallet wallet;

  @OneToOne(cascade = {
    CascadeType.PERSIST,
    CascadeType.MERGE
  })
  @JoinColumn
  private TransactionCategory category;
}
