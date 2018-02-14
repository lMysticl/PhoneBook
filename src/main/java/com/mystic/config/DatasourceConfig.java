package com.mystic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * @author Putrenkov Pavlo
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.mystic.model.repository")
public class DatasourceConfig {

    private static final String MYSQL_ADDRESS = "jdbc:mysql://localhost:3306/phonebook";
    private static final String MYSQL_PASSWORD = "root";
    private static final String MYSQL_USER = "root";

    @Bean
    public DataSource datasource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
        ds.setUrl(MYSQL_ADDRESS);
        ds.setUsername(MYSQL_USER);
        ds.setPassword(MYSQL_PASSWORD);
        return ds;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

}