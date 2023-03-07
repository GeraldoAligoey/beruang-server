package com.gma.challenge.beruang.model;

import com.gma.challenge.beruang.model.common.CommonNamedClass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Category extends CommonNamedClass {

    private boolean expense;
    private String icon;
    private String color;
    private boolean userDefined;
    private boolean active;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "categories")
    @Column(name = "wallet_id")
    Set<Wallet> wallets = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "categories")
    @Column(name = "budget_id")
    Set<Budget> budgets = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.ALL)
    private Set<Transaction> transactions = new HashSet<>();

    public Category(String name, boolean expense, String icon, String color, boolean userDefined) {
        this.expense = expense;
        this.icon = icon;
        this.color = color;
        this.userDefined = userDefined;
        this.active = true;
        super.setName(name);
    }

    public Category(
            String name,
            boolean expense,
            String icon,
            String color,
            boolean userDefined,
            boolean active) {
        this.expense = expense;
        this.icon = icon;
        this.color = color;
        this.userDefined = userDefined;
        this.active = active;
        super.setName(name);
    }

    public void addWallet(Wallet wallet) {
        wallets.add(wallet);
    }

    public void removeWallet(Wallet wallet) {
        wallets.remove(wallet);
    }

    public void addBudget(Budget budget) {
        budgets.add(budget);
    }

    public void removeBudget(Budget budget) {
        budgets.remove(budget);
    }
}
