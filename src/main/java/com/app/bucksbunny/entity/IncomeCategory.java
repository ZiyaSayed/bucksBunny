package com.app.bucksbunny.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="INCOME_CATEGORY")
public class IncomeCategory {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="icon")
    private String icon;

    @OneToOne(mappedBy = "incomeCategory")
    private IncomeCategoryMapping incomeCategoryMapping;

    @OneToMany(mappedBy = "category")
    private List<Income> income;
}
