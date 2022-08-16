package com.sample.jpa.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.miragesql.miragesql.ClasspathSqlResource;
import com.miragesql.miragesql.SqlManager;
import com.sample.jpa.config.DataSourceConfig;

public class AbstractService {
    protected static final String SQL_PREFIX = "com/sample/jpa/sql/";

    protected SqlManager sqlManager;

    public AbstractService(DataSource dataSource) {
        DataSourceConfig config = new DataSourceConfig();
        this.sqlManager = config.getSqlManager(dataSource);
    }

    protected TransactionStatus getTransactionStatus() {
        return TransactionAspectSupport.currentTransactionStatus();
    }

    protected <T> Optional<T> getSingleEntityFromExternalQuery(Class<T> clazz, String fileName,
            Map<String, String> params) {
        return Optional.ofNullable(this.sqlManager.getSingleResult(clazz, new ClasspathSqlResource(SQL_PREFIX + fileName), params));
    }

    protected <T> List<T> getListEntityFromExternalQuery(Class<T> clazz, String fileName, Map<String, String> params) {
        return this.sqlManager.getResultList(clazz, new ClasspathSqlResource(SQL_PREFIX + fileName), params);
    }
}
