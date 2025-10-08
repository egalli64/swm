-- CREATE TABLE
-- on schema ME accessing HR.EMPLOYEES
-- grant select on hr.employees to me;

-- CREATE TABLE AS inner select
create table coders
as
    select employee_id as coder_id, first_name, last_name, hire_date, salary
    from hr.employees
    where department_id = 60;

-- plain CREATE TABLE
create table simple (
    simple_id integer primary key,
    status char,
    name varchar2(20),
    coder_id integer
);

select table_name from user_tables;

select column_name, data_type, data_length, data_precision, data_scale
from user_tab_columns
where table_name = 'SIMPLE';

-- ALTER TABLE to ADD column
alter table simple
add counter number(38, 0);

describe simple;

-- ALTER TABLE to DROP COLUMN
alter table simple
drop column counter;

-- ALTER TABLE to ADD CHECK
alter table simple
add constraint simple_status_ck check (status in ('A', 'B', 'X'));

alter table simple
add constraint simple_id_ck check (mod(simple_id, 2) = 1);

-- ALTER TABLE to enforce NOT NULL check
alter table simple
modify name constraint simple_name_nn not null;

-- these should fail
insert into simple values(11, 'Z', 'bad status', 1);
insert into simple values(2, 'A', 'id should be odd', 1);
insert into simple values(3, 'A', NULL, 1);

-- DROP CONSTRAINT
alter table simple
drop constraint simple_name_nn;

-- ALTER TABLE to ADD PRIMARY KEY
alter table coders
add constraint coders_pk primary key(coder_id);

-- reset a column as FK
--

alter table simple
drop column coder_id;

-- plain FK reference
alter table simple
add constraint simple_coder_id_fk
coder_id references coders(coder_id);

-- alternative: FK with automatic orphan removal
alter table simple
add constraint simple_coder_id_fk
coder_id references coders(coder_id) on delete cascade;

-- alternative: FK with relation removal
alter table simple
add constraint simple_coder_id_fk
coder_id references coders(coder_id) on delete set null;

-- use of FK

insert into coders values(12, 'Tom', 'Jones', '9-JAN-19', 4000);

-- INSERT INTO table
insert into simple values(1, 'A', 'first', 103);
insert into simple values(3, 'A', 'second', 107);
-- this should fail: parent key not found
insert into simple values(5, 'A', 'employee?', 42);

-- set all simples as children of employee 12
update simple set coder_id = 12;

-- if fk w/on delete cascade, simple rows are removed
-- if fk w/ditto set null, fk is made invalid
delete from coders
where coder_id = 12;

-- UNIQUE
alter table simple
add constraint simple_name_uq unique(name);

alter table coders
add constraint coders_name_uq unique(first_name, last_name);


-- these should fail
insert into simple values(5, 'A', 'first', 12);
insert into coders values(5, 'Diana', 'Lorentz', '01-JAN-19', 12000);

-- check constraints
select table_name, constraint_name, status
from user_constraints
where table_name = 'SIMPLE';

-- redo it from scratch
drop table simple;

create table simple (
    simple_id integer
        constraint simple_pk primary key
        constraint simple_id_ck check (mod(simple_id, 2) = 1),
    status char default 'A'
        constraint simple_status_ck check (status in ('A', 'B', 'X')),
    name varchar2(20)
        -- constraint simple_name_nn not null,
        constraint simple_name_uq unique,
    coder_id integer
        constraint simple_coder_id_fk
        references coders(coder_id) on delete cascade
);

insert into coders values(12, 'Tom', 'Jones', '9-JAN-19', 4000);

insert into simple values(1, 'A', 'first', 12);

-- column default value
insert into simple values(3, DEFAULT, 'second', 12);
insert into simple (simple_id, name, coder_id) values(5, 'third', 103);

select * from simple;
delete from coders where coder_id = 12;

-- RENAME table
rename simple to my_status;

-- COMMENT ON TABLE
comment on table my_status is 'the state of a coder';

-- COMMENT ON COLUMN
comment on column my_status.status is 'the actual coder status';

-- danger zone
truncate table my_status;
drop table my_status;
