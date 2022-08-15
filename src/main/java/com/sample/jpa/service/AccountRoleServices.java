package com.sample.jpa.service;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.miragesql.miragesql.ClasspathSqlResource;
import com.sample.jpa.entity.Account;
import com.sample.jpa.entity.AccountRole;
import com.sample.jpa.entity.Role;
import com.sample.jpa.exception.AccountNotFoundException;
import com.sample.jpa.exception.RoleNotFoundException;
import com.sample.jpa.repository.AccountRepository;
import com.sample.jpa.repository.AccountRoleRepository;
import com.sample.jpa.repository.RoleRepository;

@Service
public class AccountRoleServices extends AbstractService {
    @Autowired
    private AccountRoleRepository accountRoleRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    public AccountRoleServices(DataSource dataSource) {
        super(dataSource);
    }

    public Iterable<AccountRole> findAll() {
        return accountRoleRepository.findAll();
    }

    public void deleteAll() {
        accountRoleRepository.deleteAll();
    }

    public void save(AccountRole... accountRoles) {
        for (AccountRole accountRole : accountRoles) {
            this.accountRoleRepository.save(accountRole);
        }
    }

    public List<Account> getAccountFromExternalQuery(String fileName, Map<String, String> params) {
        return this.sqlManager.getResultList(Account.class, new ClasspathSqlResource(SQL_PREFIX + fileName), params);
    }

    @Transactional(noRollbackFor = RoleNotFoundException.class, rollbackFor = AccountNotFoundException.class)
    @ExceptionHandler({ AccountNotFoundException.class, RoleNotFoundException.class })
    public AccountRole addAccountRole(AccountRole accountRoles) {
        Role role = roleRepository.findByRoleName(accountRoles.getRoles().getRoleName());
        if (role == null) {
            throw new RoleNotFoundException("No such role");
        }
        Account account = accountRepository.findByUsername(accountRoles.getUser().getUsername());
        if (account == null) {
            throw new AccountNotFoundException("No such account");
        }
        return accountRoleRepository.save(accountRoles);
    }
}
