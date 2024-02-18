package com.app.bucksbunny.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Entity
@Table(name="USER_INFO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column(name="user_email")
    private String userEmail;

    @Column(name="user_password")
    private String userPassword;

    @Column(name="user_name")
    private String userName;

    @Column(name="roles")
    private String roles;

    @Column(name="create_date")
    private ZonedDateTime createDate;

}
