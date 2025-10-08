-- SEQUENCE, VIEW
-- on schema ME

create sequence my_seq;

select * from user_sequences;

select my_seq.nextval
from dual;

select my_seq.currval
from dual;

alter sequence my_seq increment by 2;

drop sequence my_seq;

create sequence my_seq
start with 201 increment by 2;

-- sequences for PK
-- on simple created again as before
insert into coders values(my_seq.nextval, 'Bertrand', 'Meyer', SYSDATE, 8000);
insert into coders values(my_seq.nextval, 'Bjarne', 'Stroustrup', SYSDATE, 8000);

-- VIEW
--
-- the user should have the right to create views
-- GRANT CREATE VIEW TO me;

create or replace view odd_coders_view as
select *
from coders
where mod(coder_id, 2) = 1
with read only;

select * from odd_coders_view;

insert into odd_coders_view values(42, 'Tom', 'Jones', SYSDATE, 5000);

drop view odd_coders_view;
