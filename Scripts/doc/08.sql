-- DATE

select sysdate
from dual;

select employee_id, hire_date, to_char(hire_date, 'MONTH DD, YYYY')
from employees;

select to_char(sysdate, 'MONTH DD, YYYY, HH24:MI:SS')
from dual;

select to_date('January 4, 2019', 'MONTH DD, YYYY'), to_date('7.4.18', 'MM.DD.YY')
from dual;

select to_char(to_date('05-FEB-2019 19:32:36', 'DD-MON-YYYY HH24:MI:SS'), 'HH24:MI:SS')
from dual;

select add_months('01-DEC-2016', 13)
from dual;

-- last day of month
select last_day('01-JAN-2018')
from dual;

select months_between('25-MAY-2018', '15-JAN-2018')
from dual;

-- next specified day
select next_day('20-JAN-2019', 'SATURDAY')
from dual;

select round(to_date('25-OCT-2018'), 'YYYY'), trunc(to_date('25-OCT-2018'), 'YYYY')
from dual;
