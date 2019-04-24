-- SELECT on two tables
-- INNER JOIN

select region_name
from regions
where region_id = 1;

select country_name
from countries
where region_id = 1;

-- classic join on pk-fk relation
select region_name, country_name
from regions, countries
where regions.region_id = countries.region_id;

-- it's a NATURAL JOIN!
select region_name, country_name
from regions natural join countries;

-- explicit declaration of column
select region_name, country_name
from regions join countries
using(region_id);

-- beware of unintentional cross join
select region_name, country_name
from regions, countries;

-- modern CROSS JOIN
select region_name, country_name
from regions cross join countries;

-- sorting
select region_name, country_name
from regions natural join countries
order by 2;

-- classic join filtered: eu only
select region_name, country_name
from regions, countries
where regions.region_id = countries.region_id
and regions.region_id = 1;

-- NATURAL JOIN WHERE for filtering
select region_name, country_name
from regions natural join countries
where region_id = 1;

-- JOIN USING WHERE for filtering
select region_name, country_name
from regions join countries
using(region_id)
where region_id = 1;

-- aliases
select r.region_name, c.country_name
from regions r, countries c
where r.region_id = c.region_id
and r.region_id = 1;

-- JOIN ON: explicit declaration of relation
select r.region_name, c.country_name
from regions r join countries c
on(r.region_id = c.region_id)
where(r.region_id = 1); -- where / and

-- classic self join
select e.first_name || ' ' || e.last_name as employee,
    m.first_name || ' ' || m.last_name as manager
from employees e, employees m
where e.manager_id = m.employee_id
order by 1;

-- modern self JOIN
select e.first_name || ' ' || e.last_name as employee,
    m.first_name || ' ' || m.last_name as manager
from employees e join employees m
on (e.manager_id = m.employee_id)
order by 1;

-- classic select on more than 2 tables
select employee_id, city, department_name
from employees e, departments d, locations l
where d.department_id = e.department_id
and d.location_id = l.location_id;

-- modern select on more than 2 tables
select employee_id, city, department_name
from employees e join departments d
on d.department_id = e.department_id
join locations l
on d.location_id = l.location_id;
