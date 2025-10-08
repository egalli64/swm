--
-- subqueries:
--  inner queries returning 0,1 or many rows

-- in WHERE
--

-- manager of Chen
select first_name, last_name
from employees
where employee_id = (
    select manager_id
    from employees
    where last_name = 'Chen'
);

-- salary above average
select first_name, last_name, salary
from employees
where salary > (
    select avg(salary)
    from employees
)
order by 3 desc;

-- in HAVING
--

-- average salaries grouped by dep
select department_id, trunc(avg(salary))
from employees
group by department_id
order by 2 desc;

-- filtering out the topmost one from the previous query
select department_id, trunc(avg(salary))
from employees
group by department_id
having avg(salary) < (
    select max(avg(salary))
    from employees
    group by department_id
)
order by 2 desc;

-- in FROM (inline view)
--

-- just to see how it works
select employee_id
from (
    select employee_id
    from employees
    where employee_id between 112 and 115
);

-- count countries in each region
select r.region_name, c.country_count
from regions r, (
    select region_id, count(rowid) country_count
    from countries
    group by region_id
) c
where r.region_id = c.region_id;

-- as above, modern version
select region_name, country_count
from regions natural join (
    select region_id, count(rowid) country_count
    from countries
    group by region_id
);


-- multiple-row subqueries
-- using IN, ANY, or ALL

select *
from regions
where region_id in (1, 2, 3);

-- managers for employees named Jennifer
select employee_id, first_name, last_name
from employees
where employee_id in(
    select manager_id
    from employees
    where first_name = 'Jennifer'
);

-- non-manager employees
select employee_id, first_name, last_name
from employees
where employee_id not in(
    select distinct manager_id
    from employees
    where manager_id is not null
)
order by 2, 3;

-- ANY: true if at least one comparison succeeds

-- employees in administration with a salary less than at least a min_salary
-- (the one for president rules, 20K)
select employee_id, first_name, last_name, salary
from employees
where salary < any(
    select min_salary
    from jobs
)
and job_id like 'AD_%'
order by 1;

-- ALL: true if all comparisons succeed

-- employees with a salary higher than all min_salary
select employee_id, first_name, last_name, salary
from employees
where salary > all(
    select min_salary
    from jobs
);
