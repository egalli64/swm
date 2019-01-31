-- SELECT on two tables
-- OUTER JOIN

-- oracle classic left outer join
select e.first_name, e.last_name, d.department_name
from employees e, departments d
where e.department_id = d.department_id (+)
and last_name like 'Gr%';

-- modern LEFT OUTER JOIN
select first_name, last_name, department_name
from employees left outer join departments
using(department_id)
where last_name like 'Gr%';

-- oracle classic right outer join
select e.first_name, e.last_name, d.department_name
from employees e, departments d
where e.department_id (+) = d.department_id
and d.department_id between 110 and 120;

-- modern RIGHT OUTER JOIN
select first_name, last_name, department_name
from employees right outer join departments
using(department_id)
where department_id between 110 and 120;

-- FULL OUTER JOIN
select e.last_name, d.department_id, d.department_name
from employees e full outer join departments d
on (e.department_id = d.department_id)
where last_name like 'Gr%' or d.department_id between 110 and 120;
