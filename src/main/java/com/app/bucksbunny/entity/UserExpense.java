package com.app.bucksbunny.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="USER_EXPENSE")
public class UserExpense {

    @Id
    @Column(name="user_expense_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="user_id")
    private String userId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="expense_id", referencedColumnName = "id")
    private Expense expense; // Expense f.k
}
