package com.app.bucksbunny.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="INCOME")
public class Income {

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

    @JsonIgnore
    @OneToOne(mappedBy = "income", cascade = CascadeType.REMOVE)
    private UserIncome userIncome;

    @ManyToOne
    @JoinColumn(name="category", referencedColumnName = "id")
    private IncomeCategory category; // categoryId f.k

    @ManyToOne
    @JoinColumn(name="account_type_id", referencedColumnName = "id")
    private AccountType accountType; // accountType f.k
}
