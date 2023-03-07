package com.gma.challenge.beruang.model;

import com.gma.challenge.beruang.model.common.CommonNamedClass;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "wallet", cascade = CascadeType.ALL)
    private Set<Budget> budgets = new HashSet<>();

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(
            name = "WALLET_CATEGORY",
            joinColumns = @JoinColumn(name = "wallet_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "wallet", cascade = CascadeType.ALL)
    private Set<Transaction> transactions = new HashSet<>();

    public void addCategory(Category category) {
        categories.add(category);
    }

    public void removeCategory(Category category) {
        categories.remove(category);
    }

    public void addBudget(Budget budget) {
        budgets.add(budget);
    }

    public void removeBudget(Budget budget) {
        budgets.remove(budget);
    }
}
