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

//    @JsonIgnore
//    @Transient
//    private int monthNumber;

    @Column(name="month")
    private int month;

    @Column(name="year")
    private String year;

    @ManyToOne
    @JoinColumn(name="expense_category", referencedColumnName = "id")
    private ExpenseCategory category; // f.k


//    public void setMonthName(int monthNumber) {
//        // Set monthName based on the monthNumber
//        this.month = String.valueOf(Month.of(monthNumber));
//    }
}
