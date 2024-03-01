package com.app.bucksbunny.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="INCOME_CATEGORY_MAPPING")
public class IncomeCategoryMapping {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="user_id")
    private String userId;

    @OneToOne
    @JoinColumn(name="income_category", referencedColumnName ="id")
    private IncomeCategory incomeCategory; // incomeCategory f.k
}
