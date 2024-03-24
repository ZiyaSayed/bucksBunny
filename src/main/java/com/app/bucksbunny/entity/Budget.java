package com.app.bucksbunny.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="BUDGET")
public class Budget {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="budget_limit")
    private int budgetLimit;

    @Column(name="spent")
    private int spent;

    @ManyToOne
    @JoinColumn(name="expense_category", referencedColumnName = "id")
    private ExpenseCategory category; // f.k
}
