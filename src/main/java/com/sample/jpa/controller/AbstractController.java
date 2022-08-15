package com.sample.jpa.controller;

import javax.sql.DataSource;

import com.miragesql.miragesql.SqlManager;
import com.sample.jpa.config.DataSourceConfig;

public class AbstractController {

    protected SqlManager sqlManager;

    public AbstractController(DataSource dataSource) {
        DataSourceConfig config = new DataSourceConfig();
        this.sqlManager = config.getSqlManager(dataSource);
    }
}
