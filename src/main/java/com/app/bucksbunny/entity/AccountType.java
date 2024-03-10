package com.app.bucksbunny.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="ACCOUNT_TYPE")
public class AccountType {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="icon")
    private String icon;

    @Column(name="amount")
    private long amount;

    @JsonIgnore
    @OneToOne(mappedBy = "accountType", cascade = CascadeType.REMOVE)
    private AccountTypeMapping accountTypeMapping;

    @JsonIgnore
    @OneToMany(mappedBy = "accountType")
    private List<Income> income;

    @JsonIgnore
    @OneToMany(mappedBy = "accountType")
    private List<Expense> expense;

    @JsonIgnore
    @OneToMany(mappedBy = "toAccountType")
    private List<Transfers> toTransferType;

    @JsonIgnore
    @OneToMany(mappedBy = "fromAccountType")
    private List<Transfers> fromTransferType;

}
