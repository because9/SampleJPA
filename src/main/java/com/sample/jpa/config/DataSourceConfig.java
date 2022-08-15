package com.sample.jpa.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.miragesql.miragesql.SqlManager;
import com.miragesql.miragesql.SqlManagerImpl;
import com.miragesql.miragesql.dialect.HyperSQLDialect;
import com.miragesql.miragesql.integration.spring.SpringConnectionProvider;

@Configuration
public class DataSourceConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public DataSourceTransactionManager getDataSourceTransactionManager(DataSource datasource) {
        return new DataSourceTransactionManager(datasource);
    }

    @Bean
    public SpringConnectionProvider getConnectionProvider(DataSource datasource) {
        SpringConnectionProvider springConnectionProvider = new SpringConnectionProvider();
        springConnectionProvider.setTransactionManager(getDataSourceTransactionManager(datasource));
        return springConnectionProvider;
    }

    @Bean
    public HyperSQLDialect getDialect() {
        return new HyperSQLDialect();
    }

    @Bean
    public SqlManager getSqlManager(DataSource datasource) {
        SqlManager sqlManager = new SqlManagerImpl();
        sqlManager.setConnectionProvider(getConnectionProvider(datasource));
        sqlManager.setDialect(getDialect());
        return sqlManager;
    }
}
