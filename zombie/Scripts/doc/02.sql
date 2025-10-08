-- SELECT
-- from HR schema

-- current system date
select sysdate from dual;

-- see the names of the already existing tables
select table_name from user_tables;

-- Common Oracle Database Types
-- NUMBER(precision, scale)
-- INTEGER
-- CHAR(length)
-- VARCHAR2(length)
-- DATE

-- table details
describe jobs;

-- or ...
select column_name, nullable, data_type, data_length, data_precision, data_scale from user_tab_columns
where table_name = 'JOBS';


-- all rows in regions
select *
from regions;

-- select given column(s)
select region_name
from regions;

-- select given row(s)
select *
from regions
where region_id = 1;

-- rowid, rownum pseudo columns
select rowid, rownum
from regions;

-- Arithmetic: + - * /
select 2*6, to_date('30-AUG-2018') + 2, to_date('02-AUG-2018') - 3,
    to_date('02-AUG-2018') - to_date('25-JUL-2018')
from dual;

select job_title, min_salary, min_salary + 2000, min_salary * 3 + 1000
from jobs;

-- aliases
select job_title, min_salary as original, min_salary + 2000 "with increase"
from jobs;

-- Concatenation
select first_name || ' ' || last_name as "Employee's name"
from employees;

-- Null
select first_name, last_name, commission_pct
from employees
where commission_pct is null;

select first_name, last_name, 12*salary*commission_pct
from employees;

-- NVL()
select first_name, last_name, nvl(commission_pct, 0)
from employees;

select first_name, last_name, 12*salary*nvl(commission_pct, 0)
from employees;

-- DISTINCT
select manager_id
from employees;

select distinct manager_id
from employees;
