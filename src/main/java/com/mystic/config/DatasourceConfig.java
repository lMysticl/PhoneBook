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
import java.beans.PropertyVetoException;

/**
 * Created by nydiarra on 06/05/17.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.mystic.model.repository")
public class DatasourceConfig {


//    @Bean
//    public PersistentTokenRepository persistentTokenRepository() {
//        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
//        db.setDataSource(dataSource);
//        return db;
//    }


//    @Bean
//    public DataSource datasource() throws PropertyVetoException {
//        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
//        EmbeddedDatabase dataSource = builder
//                .setType(EmbeddedDatabaseType.H2)
//                .addScript("sql-scripts/schema.sql")
//                .addScript("sql-scripts/data.sql")
//                .build();
//
//        return dataSource;
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("datasource") DataSource ds) throws PropertyVetoException{
//        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
//        entityManagerFactory.setDataSource(ds);
//        entityManagerFactory.setPackagesToScan(new String[]{"com.nouhoun.springboot.jwt.integration.domain"});
//        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
//        entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter);
//        return entityManagerFactory;
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(entityManagerFactory);
//        return transactionManager;
//    }



    @Bean
    public DataSource datasource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
        ds.setUrl("jdbc:mysql://localhost:3306/phonebook");
        ds.setUsername("root");
        ds.setPassword("root");
        return ds;
    }

//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("datasource") DataSource ds) throws PropertyVetoException {
//        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
//        entityManagerFactory.setDataSource(ds);
//        entityManagerFactory.setPackagesToScan("com.mystic.model.entity");
//        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
//        entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter);
//        return entityManagerFactory;
//    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

}