package com.app.bucksbunny.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="EXPENSE_CATEGORY")
public class ExpenseCategory {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="icon")
    private String icon;

    @OneToOne
    @JoinColumn(name="budget", referencedColumnName = "id")
    private Budget budget; // budget f.k

    @OneToMany(mappedBy = "category")
    private List<Expense> expense;

    @OneToMany(mappedBy = "expenseCategory")
    private List<ExpenseCategoryMapping> expenseCategoryMapping;
}
