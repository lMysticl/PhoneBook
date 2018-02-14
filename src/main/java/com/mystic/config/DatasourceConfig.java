package com.mystic.config;

import org.springframework.beans.factory.annotation.Value;
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

    @Value("${spring.datasource.url}")
    private String MYSQL_ADDRESS = "jdbc:mysql://localhost:3306/phonebook";

    @Value("${spring.datasource.password}")
    private String MYSQL_PASSWORD;
    @Value("${spring.datasource.username}")
    private String MYSQL_USER;

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