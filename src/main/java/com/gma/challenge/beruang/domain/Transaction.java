package com.gma.challenge.beruang.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.gma.challenge.beruang.domain.common.CommonClass;

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
public class Transaction extends CommonClass {
  
  private String note;
  private BigDecimal amount;
  private LocalDate date;

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
  private Category category;
}
