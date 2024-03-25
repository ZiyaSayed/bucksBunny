package com.app.bucksbunny.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="EXPENSE_CATEGORY_MAPPING")
public class ExpenseCategoryMapping {

    @Id
    @Column(name="expense_category_mapping_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name="user_id")
    private String userId;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="expense_category", referencedColumnName = "id")
    private ExpenseCategory expenseCategory; // userExpenseCategory f.k

}
