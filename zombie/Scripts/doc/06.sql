--
-- single-row functions
--

-- character functions
--
select ascii('A') A, chr(90) "90"
from dual;

select concat(first_name, last_name)
from employees;

-- instr() position in string - 0 means not found
select region_name, instr(region_name, 'Middle'), instr(region_name, 'East')
from regions
where region_id = 4;

-- position of second 'a' starting from index 10 in name
select region_name, instr(region_name, 'a', 10, 2)
from regions
where region_id = 4;

-- implicit conversion from date to string
select employee_id, hire_date, instr(hire_date, 'JAN')
from employees
where employee_id = 102;

select region_name, length(region_name)
from regions;

-- implicit conversion from number to string
select salary, length(salary)
from employees;

-- capitalization
select upper(first_name), lower(last_name), initcap(first_name || ' o''' || last_name)
from employees
where last_name not like 'O%';

-- padding
select rpad(first_name, 30, '.') first, lpad(last_name, 30, '_-') last
from employees
where last_name like '_a%s';

-- trimming
select ltrim(' Hi!'), rtrim('Hi!abab', 'ab'), trim('0' from '00Hi!000')
from dual;

-- null to value
select employee_id, nvl(commission_pct, 0)
from employees;

-- if not null ... else ...
select employee_id, nvl2(commission_pct, 'value', 'no value')
from employees;

select job_title, replace(job_title, 'Administration', 'Admin')
from jobs
where job_id like 'AD%';

-- substring from with size
select job_title, substr(job_title, 16, 4)
from jobs
where job_id = 'AD_VP';

-- combining functions
select job_title, upper(substr(job_title, 16, 4))
from jobs
where job_id = 'AD_VP';

-- numeric functions
--
select abs(10), abs(-10),
    ceil(5.8), ceil(-5.2),
    floor(5.8), floor(-5.2),
    mod(8, 3), mod(8, 4),
    power(2, 1), power(2, 3)
from dual;

select round(5.75), round(5.75, 1), round(5.75, -1),
    sign(-5), sign(5), sign(0)
from dual;

select sqrt(25), sqrt(5),
    trunc(5.75), trunc(5.75, 1), trunc(5.75, -1)
from dual;

-- conversion functions
--

select to_char(12345.67), to_char(12345.67, '99,999.99'),
    to_char(2019, 'RN'), to_number('970,13') * 2,
    cast('05-JUL-18' as date) + 2, cast(12345.678 as number(10,2))
from dual;
