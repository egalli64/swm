--
-- Data Manipulation Language (DML): INSERT, UPDATE, DELETE
--
select * from regions;

-- INSERT

insert into regions(region_id, region_name)
values (11, 'Antarctica');

insert into regions(region_id)
values (12);

insert into regions values (13, null);

-- UPDATE (WHERE!)

update regions
set region_name = 'Oceania'
where region_id = 12;

update regions
set
    region_name = 'Region ' || region_id
where region_id >= 10;

-- DELETE (WHERE!)

delete from regions
where region_id >= 10;

-- database transactions
-- COMMIT, ROLLBACK (TO SAVEPOINT)

insert into regions(region_id, region_name)
values (11, 'Antarctica');

-- inserting was a mistake
rollback;

insert into regions(region_id, region_name)
values (11, 'Antarctica');

savepoint sp;

insert into regions(region_id, region_name)
values (12, 'Oceania');

-- Antarctica is ok, but Oceania was a mistake
rollback to sp;

-- actually, Antarctica was a mistake too
rollback;