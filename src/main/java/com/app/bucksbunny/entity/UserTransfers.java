package com.app.bucksbunny.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="USER_TRANSFERS")
public class UserTransfers {

    @Id
    @Column(name="user_transfers_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="user_id")
    private String userId;

    @OneToOne
    @JoinColumn(name="transfer_id", referencedColumnName = "id")
    private Transfers transfer; // transferId f.k
}
