package com.sample.jpa.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miragesql.miragesql.ClasspathSqlResource;
import com.sample.jpa.entity.Account;
import com.sample.jpa.repository.AccountRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class AccountController extends AbstractController {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    public AccountController(DataSource dataSource) {
        super(dataSource);
    }

    @RequestMapping("/")
    @Transactional
    public void addSampleData() {
        // save a few Accounts
        Account a = createAccount("user A", "123456", "userA@gmail.com", new Timestamp(System.currentTimeMillis()));
        accountRepository.save(a);
        a = createAccount("user B", "123456", "userB@gmail.com", new Timestamp(System.currentTimeMillis()));
        accountRepository.save(a);
        a = createAccount("user C", "123456", "userC@gmail.com", new Timestamp(System.currentTimeMillis()));
        accountRepository.save(a);

        // fetch all Accounts
        log.info("Accounts found with findAll():");
        log.info("-------------------------------");
        for (Account account : accountRepository.findAll()) {
            log.info(account.toString());
        }

        List<Account> accounts = this.sqlManager.getResultList(Account.class,
                new ClasspathSqlResource("com/sample/jpa/sql/sample-sql.sql"));
        accounts.forEach(System.out::println);
        accountRepository.deleteAll();
        log.info("");
    }

    private Account createAccount(String username, String password, String email, Timestamp createOn) {
        Account a = new Account();
        a.setUsername(username);
        a.setPassword(password);
        a.setEmail(email);
        a.setCreateOn(createOn);
        return a;
    }
}
