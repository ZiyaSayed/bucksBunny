package com.app.bucksbunny.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="TRANSFERS")
public class Transfers {

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
    @OneToOne(mappedBy = "transfer", cascade = CascadeType.REMOVE)
    private UserTransfers userTransfers;

    @ManyToOne
    @JoinColumn(name="to_account_type", referencedColumnName = "id")
    private AccountType toAccountType; // accountType f.k

    @ManyToOne
    @JoinColumn(name="from_account_type", referencedColumnName = "id")
    private AccountType fromAccountType; // accountType f.k

}
