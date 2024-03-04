package com.app.bucksbunny.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="ACCOUNT_TYPE_MAPPING")
public class AccountTypeMapping {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="user_id")
    private String userId;

    @ManyToOne
    @JoinColumn(name="account_type", referencedColumnName = "id")
    private AccountType accountType; // accountType f.k

}
