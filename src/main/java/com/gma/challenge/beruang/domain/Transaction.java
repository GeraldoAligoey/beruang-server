package com.gma.challenge.beruang.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn
  private Wallet wallet;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn
  private Category category;
}
