-- hr has granted select on countries, regions, and employees to me
create table regions(
    region_id number constraint regions_pk primary key, 
    region_name varchar2(25)
);

insert into regions (
    select * from hr.regions
);

create table countries(
    country_id char(2) constraint countries_pk primary key, 
	country_name varchar2(40), 
	region_id number constraint countries_region_fk references regions(region_id)
);

insert into countries(
    select * from hr.countries
);

create table employees(
    employee_id number(6,0) constraint employees_pk primary key,
    first_name varchar2(20 byte), 
    last_name varchar2(25 byte) constraint employees_last_name_nn not null,
    email varchar2(25 byte) constraint employees_email_emp_email_uq unique, 
    hire_date date constraint employees_hire_date_nn not null, 
    job_id varchar2(10) constraint employees_job_nn not null,

    constraint employees_name_uq unique (first_name, last_name)
);

insert into employees (
    select employee_id, first_name, last_name, email, hire_date, job_id
    from hr.employees
);

create sequence employees_seq increment by 1 start with 300;

-- ManyToMany
create table organizations(
	org_id number constraint organizations_pk primary key,
	org_name varchar2(20) not null
);

create table org_country(
	org_id number constraint oc_org_fk references organizations(org_id) on delete cascade,
	country_id char(2) constraint oc_country_fk references countries(country_id) on delete cascade,
	
	primary key(org_id, country_id)
);

insert into organizations values(1, 'EU');
insert into organizations values(2, 'NATO');
insert into org_country values(1, 'IT');
insert into org_country values(1, 'FR');
insert into org_country values(2, 'IT');
insert into org_country values(2, 'FR');
insert into org_country values(2, 'US');

-- select c.country_name, o.org_name
-- from org_country natural join organizations o natural join countries c
-- order by 1, 2;

