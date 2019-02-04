--
-- Sequence Table Generator

create table x1_employees(
    id integer primary key
);

create table x_table_generator(
    sequence_name varchar(20),
    next_val integer
);

-- drop table x1_employees;
-- drop table x_table_generator;

--
-- OneToOne

create table x2_workstations(
    wst_id integer primary key
);

-- drop table x2_workstations;
select * from x2_workstations;

create table x2_employees(
    emp_id integer primary key,
    wst_id integer referencing x2_workstations(wst_id) unique
);

-- drop table x2_employees;

--
-- ManyToOne

create table x3_departments(
    dep_id integer primary key
);

create table x3_employees(
    emp_id integer primary key,
    dep_id integer referencing x3_departments(dep_id)
);

