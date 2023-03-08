package com.gma.challenge.beruang.budget.model;

import com.gma.challenge.beruang.category.model.Category;
import com.gma.challenge.beruang.common.model.CommonNamedClass;
import com.gma.challenge.beruang.wallet.model.Wallet;
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
public class Budget extends CommonNamedClass {

    private String period;
    private BigDecimal limitAmount = BigDecimal.ZERO;
    private BigDecimal currentAmount = BigDecimal.ZERO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Wallet wallet;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(
            name = "BUDGET_CATEGORY",
            joinColumns = @JoinColumn(name = "budget_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    public void addCategory(Category category) {
        categories.add(category);
    }

    public void removeCategory(Category category) {
        categories.remove(category);
    }
}
