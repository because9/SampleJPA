package com.sample.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.jpa.entity.Role;
import com.sample.jpa.service.RoleServices;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleServices roleServices;

    @PostMapping("/add")
    public Role addRole(@RequestBody(required = true) Role roles) {
        return roleServices.save(roles);
    }
}
