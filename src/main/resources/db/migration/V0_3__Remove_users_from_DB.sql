alter table ticket drop constraint ticket_user_id_fkey;
alter table ticket alter column user_id type bigint;

drop table users;
drop table team;
