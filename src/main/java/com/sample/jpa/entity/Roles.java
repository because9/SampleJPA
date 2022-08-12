package com.sample.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Roles {
    @Id
    @GeneratedValue
    private Long roleId;
    @Column(name = "role_name", nullable = false, length = 255, unique = true)
    private String roleName;
}
