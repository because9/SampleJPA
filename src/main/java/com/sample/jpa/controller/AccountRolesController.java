package com.sample.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.jpa.entity.AccountRole;
import com.sample.jpa.service.AccountRoleServices;

@RestController
@RequestMapping("/account-role")
public class AccountRolesController {
    @Autowired
    private AccountRoleServices accountRoleServices;

    @PostMapping("/add")
    public AccountRole addAccountRole(@RequestBody(required = true) AccountRole accountRole) {
        return accountRoleServices.addAccountRole(accountRole);
    }
}
