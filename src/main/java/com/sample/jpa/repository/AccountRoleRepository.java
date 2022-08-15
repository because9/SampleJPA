package com.sample.jpa.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sample.jpa.entity.Account;
import com.sample.jpa.entity.AccountRole;
import com.sample.jpa.entity.Role;

@Repository
public interface AccountRoleRepository extends CrudRepository<AccountRole, Long> {
    List<AccountRole> findByRoles(String roleName);

    List<AccountRole> findByUser(String user);

    AccountRole findByUserAndRoles(Account user, Role role);
}
