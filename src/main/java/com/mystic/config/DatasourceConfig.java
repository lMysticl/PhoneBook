package com.mystic.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;

/**
 * @author Putrenkov Pavlo
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.mystic.model.repository")
public class DatasourceConfig {

    @Value("${spring.datasource.url}")
    private String MYSQL_ADDRESS ;

    @Value("${spring.datasource.password}")
    private String MYSQL_PASSWORD;
    @Value("${spring.datasource.username}")
    private String MYSQL_USER;

    @Value("${spring.datasource.databaseschema}")
    private String MYSQL_SCHEMA;


//    @Bean
//    public DataSource datasource() {
//        DriverManagerDataSource ds = new DriverManagerDataSource();
//        ds.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
//        ds.setUrl(MYSQL_ADDRESS);
//        ds.setSchema(MYSQL_SCHEMA);
//        ds.setUsername(MYSQL_USER);
//        ds.setPassword(MYSQL_PASSWORD);
//        return ds;
//    }

//    @Bean
//    public BasicDataSource dataSource() throws URISyntaxException {
//        URI dbUri = new URI(System.getenv("CLEARDB_DATABASE_URL"));
//
//        String username = dbUri.getUserInfo().split(":")[0];
//        String password = dbUri.getUserInfo().split(":")[1];
//        String dbUrl = "jdbc:mysql://" + dbUri.getHost() + dbUri.getPath();
//
//        BasicDataSource basicDataSource = new BasicDataSource();
//        basicDataSource.setUrl(dbUrl);
//        basicDataSource.setUsername(username);
//        basicDataSource.setPassword(password);
//
//        return basicDataSource;
//    }
    @Bean
    public BasicDataSource dataSource() {
//        URI dbUri = new URI(MYSQL_ADDRESS);
//
//        String username = dbUri.getUserInfo().split(":")[0];
//        String password = dbUri.getUserInfo().split(":")[1];
//        String dbUrl = "jdbc:mysql://" + dbUri.getHost() + dbUri.getPath();

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(MYSQL_ADDRESS);
        basicDataSource.setUsername(MYSQL_USER);
        basicDataSource.setPassword(MYSQL_PASSWORD);

        return basicDataSource;
    }




    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

}