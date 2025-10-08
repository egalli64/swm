-- aggregate functions
--

select avg(salary), variance(salary), stddev(salary)
from employees;

select avg(distinct salary)
from employees;

-- beware of null
select count(employee_id), count(rowid), count(department_id)
from employees;

select sum(salary), max(salary), min(salary),
    max(first_name), min(last_name),
    max(hire_date), min(hire_date)
from employees;

-- grouping rows
--

-- apply function only to some rows
select avg(salary), stddev(salary)
from employees
where department_id = 30;

-- use column values to divide the table in groups
select department_id
from employees
group by department_id;

-- the result of previous GROUP BY is
-- the same of SELECT DISTINCT but the meaning is different
select distinct department_id
from employees;

-- apply aggregate function to each group
select department_id, count(rowid)
from employees
group by department_id;

select department_id, trunc(avg(salary))
from employees
group by department_id
order by 1;

-- HAVING
--

-- GROUP BY, then filter by HAVING
select manager_id, trunc(avg(salary))
from employees
group by manager_id
having avg(salary) > 6000
order by 2 desc;

-- filter by WHERE, then GROUP BY
select manager_id, trunc(avg(salary))
from employees
where salary < 8000
group by manager_id
order by 2 desc;

-- filter by WHERE, then GROUP BY, and then filter the groups by HAVING
select manager_id, trunc(avg(salary))
from employees
where salary < 8000
group by manager_id
having avg(salary) > 6000
order by 2 desc;

