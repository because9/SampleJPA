package com.sample.jpa.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "account_roles")
@Getter
@Setter
public class AccountRole {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    @JoinColumn(name = "userId", nullable = false)
    private Account user;
    @OneToOne
    @JoinColumn(name = "roleId", nullable = false)
    private Role roles;
    @Column(name = "grant_date")
    private Timestamp grantDate;
}
