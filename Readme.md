PhoneBook
========

MySql server SetUp
--------------------
CREATE SCHEMA `phonebook` ;

CREATE TABLE `phonebook`.`users` (\n
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `firstname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `middlename` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC)
  );

CREATE TABLE `phonebook`.`contacts` (
  `contact_id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `firstname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `middlename` VARCHAR(45) NOT NULL,
  `mobile_phone` VARCHAR(45) NOT NULL,
  `home_phone` VARCHAR(45) NULL,
  `address` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  PRIMARY KEY (`contact_id`),
  UNIQUE INDEX `contact_id_UNIQUE` (`contact_id` ASC));


============================

Set MySQL server configuration:
--------------------
Change properties in application.properties (src\main\resources\application.properties)
spring.datasource.url - url to server
spring.datasource.username= server username
spring.datasource.password= server password


===================================

Start app:
---------
You must run the command on the command line

If install Maven: mvn spring-boot:run

Without maven installed: mvnw spring-boot:run


