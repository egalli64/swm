-- create a user (having such privilege)
create user me identified by password;

-- the new user wants to connect and create tables 
grant create session, create table to me;

-- change user password
alter user me identified by another_password;

-- kill user
drop user me;

-- kill user and remove its objects (table ...)
drop user me cascade;
