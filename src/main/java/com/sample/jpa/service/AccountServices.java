package com.sample.jpa.service;

import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.jpa.entity.Account;
import com.sample.jpa.entity.AccountRole;
import com.sample.jpa.entity.Role;
import com.sample.jpa.exception.RoleNotFoundException;
import com.sample.jpa.repository.AccountRepository;
import com.sample.jpa.repository.AccountRoleRepository;
import com.sample.jpa.repository.RoleRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccountServices extends AbstractService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AccountRoleRepository accountRoleRepository;

    @Autowired
    public AccountServices(DataSource dataSource) {
        super(dataSource);
    }

    public Iterable<Account> findAll() {
        return accountRepository.findAll();
    }

    // require @Transactional for using this feature
    @Transactional
    public List<Account> findAllAccountUsingExternalQuery() {
        return getListEntityFromExternalQuery(Account.class, "sample-sql.sql", null);
    }

    public void deleteAll() {
        accountRepository.deleteAll();
    }

    public void save(Account... accounts) {
        for (Account account : accounts) {
            this.accountRepository.save(account);
        }
    }

    @Transactional(noRollbackFor = IllegalAccessError.class)
    // @Transactional(rollbackFor = IllegalAccessError.class)
    public Account addAccount(Account account) {
        Timestamp createdOn = new Timestamp(System.currentTimeMillis());
        account.setCreatedOn(createdOn);
        Role role = roleRepository.findByRoleName("Regular");
        if (role == null) {
            throw new RoleNotFoundException("No such role");
        }
        accountRepository.save(account);
        AccountRole accountRoleDB = accountRoleRepository.findByUserAndRoles(account, role);
        if (accountRoleDB != null) {
            throw new IllegalAccessError();
        }
        AccountRole accountRole = new AccountRole();
        accountRole.setUser(account);
        accountRole.setRoles(role);
        accountRole.setGrantDate(createdOn);
        accountRoleRepository.save(accountRole);
        log.info("Create account successed!", account);
        throw new IllegalAccessError();
    }
}
