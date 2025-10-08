-- more on SELECT - WHERE
--

--
-- Compare: =, <> !=, <, >, <=, >=, ANY, ALL
--
select *
from regions
where region_id = 1;

select *
from regions
where region_id != 2;

select *
from regions
where region_id < 3;

select *
from regions
where region_id <= 3;

-- at least one condition in ANY should be satisfied 
select *
from regions
where region_id > any(1, 2, 3);

-- every condition in ALL should be satisfied
select *
from regions
where region_id > all(1, 2, 3);

--
-- SQL Operators: (NOT) LIKE, IN, BETWEEN, IS NULL
--
select last_name
from employees
where last_name like '_ul%';

select last_name
from employees
where last_name not like '%a%';

select employee_id, first_name, last_name
from employees
Where employee_id in (102, 103, 105);

select *
from regions
where region_id not in (2, 3);

-- beware of null!
select *
from regions
where region_id not in (2, 3, null);

select *
from employees
where manager_id = null;

select *
from regions
where region_id is not null;


select *
from regions
where region_id between 2 and 3;

--
-- Logical Operators: AND, OR, NOT
--

select *
from employees
where hire_date > '01-JAN-2008' and employee_id > 195;

select *
from employees
where hire_date > '01-JAN-2008' or employee_id > 195;

select employee_id, first_name, last_name, department_id
from employees
where department_id not in (30, 50, 60, 80, 100);

-- ORDER BY
--
select last_name, first_name
from employees
order by last_name;

select last_name, first_name
from employees
order by last_name desc, first_name asc;

select first_name, last_name
from employees
order by 2;
