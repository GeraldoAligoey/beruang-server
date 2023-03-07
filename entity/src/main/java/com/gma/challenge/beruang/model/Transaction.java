package com.gma.challenge.beruang.model;

import com.gma.challenge.beruang.model.common.CommonClass;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;

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
