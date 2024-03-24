package com.app.bucksbunny.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Budget> budget;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Expense> expense;

    @JsonIgnore
    @OneToOne(mappedBy = "expenseCategory", cascade = CascadeType.REMOVE)
    private ExpenseCategoryMapping expenseCategoryMapping;
}
