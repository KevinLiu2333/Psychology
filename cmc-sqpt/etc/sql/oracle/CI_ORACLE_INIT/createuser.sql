-- Create the user 
create user cmcsqpt
  identified by cmcsqpt
  default tablespace CMCAPPLYSPACE
  temporary tablespace TEMP
  profile DEFAULT;
-- Grant/Revoke role privileges 
grant dba to cmcsqpt;
grant connect to cmcsqpt;
-- Grant/Revoke system privileges 
grant analyze any to cmcsqpt;
grant create any synonym to cmcsqpt;
grant create database link to cmcsqpt;
grant create materialized view to cmcsqpt;
grant create procedure to cmcsqpt;
grant create public synonym to cmcsqpt;
grant create sequence to cmcsqpt;
grant create synonym to cmcsqpt;
grant create table to cmcsqpt;
grant create trigger to cmcsqpt;
grant create view to cmcsqpt;
grant debug any procedure to cmcsqpt;
grant debug connect session to cmcsqpt;
grant import full database to cmcsqpt;
grant select any dictionary to cmcsqpt;
grant select any table to cmcsqpt;
grant drop any table to cmcsqpt;
grant drop any synonym to cmcsqpt;
