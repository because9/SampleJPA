package com.sample.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sample.jpa.entity.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    Account findByUsername(String userName);
}
