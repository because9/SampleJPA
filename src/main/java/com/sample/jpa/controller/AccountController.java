package com.sample.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.jpa.entity.Account;
import com.sample.jpa.service.AccountServices;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountServices accountServices;

    @GetMapping("/")
    public Iterable<Account> getAllAccounts() {
        return accountServices.findAll();
    }

    @GetMapping("/get-all")
    public List<Account> findAllAccountUsingExternalQuery() {
        return accountServices.findAllAccountUsingExternalQuery();
    }

    @PostMapping("/add")
    public Account addAccount(@RequestBody(required = true) Account account) {
        return accountServices.addAccount(account);
    }
}
