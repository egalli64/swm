--
-- sequence table generator

create table x_table_generator(
    sequence_name varchar(20),
    next_val integer
);

create table x1_employees(
    id integer primary key
);

--
-- OneToOne

create table x2_workstations(
    wst_id integer primary key
);

create table x2_employees(
    emp_id integer primary key,
    wst_id integer referencing x2_workstations(wst_id) unique
);

--
-- ManyToOne

create table x3_departments(
    dep_id integer primary key
);

create table x3_employees(
    emp_id integer primary key,
    dep_id integer referencing x3_departments(dep_id)
);

--
-- ManyToMany

create table x4_employees(
    emp_id integer primary key
);

create table x4_projects(
    prj_id integer primary key
);

create table x4_emp_prj(
    emp_id integer references x4_employees(emp_id) on delete cascade,
    prj_id integer references x4_projects(prj_id) on delete cascade,
	
	primary key(emp_id, prj_id)
);

--
-- unidirectional one-to-many

create table x5_departments(
    dep_id integer primary key
);

create table x5_employees(
    emp_id integer primary key
);

create table x5_dep_emp(
    dep_id integer references x5_departments(dep_id) on delete cascade,
    emp_id integer references x5_employees(emp_id) on delete cascade unique,
	
	primary key(dep_id, emp_id)
);

--
-- collection

create table x6_employees(
    emp_id integer primary key
);

create table x6_employee_phones(
    emp_id integer references x6_employees(emp_id) on delete cascade,
	phone varchar2(10),

	primary key(emp_id, phone)	
);


--
-- MAP

create table x7_employees(
    emp_id integer primary key
);

create table x7_employee_phones(
    emp_id integer references x6_employees(emp_id) on delete cascade,
	phn_type varchar2(4),
	phn_number varchar2(10),

	primary key(emp_id, phn_type)	
);
