package com.sample.jpa.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miragesql.miragesql.ClasspathSqlResource;
import com.miragesql.miragesql.SqlManager;
import com.miragesql.miragesql.session.Session;
import com.sample.jpa.entity.Account;
import com.sample.jpa.repository.AccountRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping("/")
    public void addSampleData() {
        accountRepository.deleteAll();
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

        Session session = SessionFactory.getSession();
        SqlManager sqlManager = session.getSqlManager();
        session.begin();

        try {
            Map<String, String> param = new HashMap<String, String>();

            List<Account> result = sqlManager.getResultList(Account.class,
                    new ClasspathSqlResource("/META-INF/sample-sql.sql"), param);

            result.forEach(System.out::println);

            session.commit();

        } catch (Exception ex) {
            session.rollback();

        } finally {
            session.release();
        }

        List<Account> accounts = sqlManager.getResultList(Account.class,
                new ClasspathSqlResource("/sql/sample-sql.sql"));
        accounts.forEach(System.out::println);
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
