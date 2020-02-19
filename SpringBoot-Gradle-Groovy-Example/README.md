![image](https://user-images.githubusercontent.com/8769673/74795420-e02c1900-52eb-11ea-8b17-bd4280041d31.png)

# SpringBoot with LiquiBase using Groovy 
# Build by Gradle

## 1. Overview
Database schema migration is performed on a database whenever it is necessary to update/revert changes in the database or need to migrate the database from one version to other. There are two major migration tools: Liquibase, Flyway.

In this tutorial, we will go with the Liquibase.  The Liquibase is open source tool for database schema migration or database version controlling. It supports most of the major databases and different type of format for schema change file like XML, SQL, YAML, JSON. It is database vendor independent means it does not depend on any DB specific syntax. It can generate database change documentation.

In this tutorial, we will learn how to configure Liquibase with Spring Boot project. Here, we will write database schema changes in XML format. Those schema changes will be automatically performed on the configured database using liquibase when spring boot project will be started.

Here, we will consider 3 simple database operations:
1). Create table,
2). Insert data into table and
3). Update data on precondition.
Those operations will be executed by liquibase on H2 database with spring boot.

## 2. Example
### 2.1 Project Structure
![image](https://user-images.githubusercontent.com/8769673/74806289-97845800-530b-11ea-8d4a-e5566ef4d6c5.png)


