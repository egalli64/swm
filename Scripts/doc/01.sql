-- Oracle setup

-- SQL statements
-- Query: SELECT
-- Data Manipulation Language (DML): INSERT, UPDATE, DELETE
-- Data Definition Language (DDL): CREATE, ALTER, DROP, RENAME, TRUNCATE
-- Transaction Control (TC): COMMIT, ROLLBACK, SAVEPOINT
-- Data Control Language (DCL): GRANT, REVOKE

-- CREATE/ALTER unlock users
--

-- connect with sqlplus, user root as sysdba
-- go to orclpdb
alter session set container = orclpdb;

-- see database status
select name, open_mode from v$pdbs;

-- to open the current database
alter database open

-- unlock hr
alter user hr identified by hr account unlock;

-- create user me
create user me identified by password account unlock;
grant connect, resource to me;
alter user me quota unlimited on users;

-- TNSNAMES.ORA
-- match pdb w/ relative service
-- ORCLPDB =
--   (DESCRIPTION =
--     (ADDRESS = (PROTOCOL = TCP)(HOST = localhost)(PORT = 1521))
--     (CONNECT_DATA =
--       (SERVER = DEDICATED)
--       (SERVICE_NAME = orclpdb)
--     )
--   )

-- refresh the oracle listener (as windows admin)
-- lsnrctl reload


-- now we can connect to sqlplus as hr/hr@orclpdb me/password@orclpdb

-- SQL Developer setup
-- Eclipse setup - DBeaver
-- Maven setup
-- GitHub

-- Where is ojdbc8.jar?
-- ex: \OracleHomeUser\product\12.2.0\dbhome_1\jdbc\lib
-- push it to your local maven repository as com.oracle.jdbc 8
-- mvn install:install-file -Dfile=/OracleHomeUser/product/12.2.0/dbhome_1/jdbc/lib/ojdbc8.jar -DgroupId=com.oracle -DartifactId=jdbc -Dversion=8 -Dpackaging=jar

-- mavenize a project and check its pom
