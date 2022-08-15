package com.sample.jpa.service;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.jpa.entity.Role;
import com.sample.jpa.repository.RoleRepository;

@Service
public class RoleServices extends AbstractService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    public RoleServices(DataSource dataSource) {
        super(dataSource);
    }

    public Role findByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    public Role save(Role roles) {
        return roleRepository.save(roles);
    }
}
