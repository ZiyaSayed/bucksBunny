package com.app.bucksbunny.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="EXPENSE")
public class Expense {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="amount")
    private int amount;

    @Column(name="notes")
    private String notes;

    @Column(name="date")
    private LocalDate date;

    @Column(name="time")
    private LocalTime time;

    @OneToOne(mappedBy = "expense")
    private UserExpense userExpense;

    @ManyToOne
    @JoinColumn(name="account_type_id", referencedColumnName = "id")
    private AccountType accountType; // accountType f.k

    @ManyToOne
    @JoinColumn(name="category", referencedColumnName = "id")
    private ExpenseCategory category; // category f.k
}
