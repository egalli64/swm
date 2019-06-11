-- PL/SQL procedure, function, trigger
-- on schema ME
-- grant create procedure to me;
-- grant create trigger to me;
set serveroutput on

-- PROCEDURE
--

create or replace procedure get_coder_salary(
    p_coder_id in coders.coder_id%type,
    p_salary out coders.salary%type) is
begin
    select salary
    into p_salary
    from coders
    where coder_id = p_coder_id;
end get_coder_salary;
/

create procedure update_coder_salary(
    p_coder_id in coders.coder_id%type,
    p_factor in number
) as
    v_count integer;
begin
    select count(rowid)
    into v_count
    from coders
    where coder_id = p_coder_id;

    if v_count = 1 then
        update coders
        set salary = salary * p_factor
        where coder_id = p_coder_id;
        commit;
    end if;
exception
    when others then
        rollback;
end update_coder_salary;
/

select coder_id, salary
from coders
where coder_id in (103, 104);

call update_coder_salary(103, 1.5);
call update_coder_salary(p_coder_id => 104, p_factor => 1.3);

DECLARE
BEGIN
    update_coder_salary(103, 1.5);

    -- etc
END;
/

drop procedure update_coder_salary;

--
--

CREATE OR REPLACE PROCEDURE get_coder_name(
    p_coder_id in coders.coder_id%type,
    p_first_name OUT coders.first_name%TYPE,
    p_last_name OUT coders.last_name%TYPE
)
IS
BEGIN
    SELECT first_name, last_name
    INTO p_first_name, p_last_name
    FROM coders
    WHERE coder_id = p_coder_id;
END get_coder_name;
/

--VARIABLE g_first_name VARCHAR2(20)
--VARIABLE g_last_name VARCHAR2(25)
--
--EXECUTE get_coder_name(105, :g_first_name, :g_last_name)
--PRINT g_first_name g_last_name

DECLARE
    v_first_name VARCHAR2(20);
    v_last_name VARCHAR2(25);
BEGIN
    get_coder_name(103, v_first_name, v_last_name);
    dbms_output.put_line(v_first_name || ' ' || v_last_name);
END;
/


-- FUNCTION
--
create function circle_area (
    p_radius in number
) return number as
    v_pi number := 3.1415926;
    v_area number;
begin
    v_area := v_pi * p_radius ** 2;
    return v_area;
end circle_area;
/

select circle_area(2)
from dual;

drop function circle_area;

-- TRIGGER
--

create table coder_salaries (
    coder_id number(6, 0) constraint coder_salaries_fk references coders(coder_id),
    old_salary number(8, 2),
    new_salary number(8, 2)
);

create table coder_salaries (
    coder_id number(6, 0)
        references coders(coder_id),
    old_salary number(8, 2),
    new_salary number(8, 2)
);


drop table coder_salaries;

select * from coder_salaries;
select * from coders;

create or replace trigger before_coders_salary_update
before update of salary on coders
for each row
begin
    insert into coder_salaries values(
        :old.coder_id, :old.salary, :new.salary);
end before_coders_salary_update;
/

create or replace trigger salary_update
before update of salary on coders
for each row
begin
    insert into coder_salaries values(
        :old.coder_id, :old.salary, :new.salary);
end salary_update;
/


update coders
set salary = salary * 1.3
where coder_id > 103;

drop trigger before_coders_salary_update;
