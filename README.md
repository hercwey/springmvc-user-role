# springmvc-user-role
spring mvc use hibernate many-to-many to manage user role
- if use <prop key="hibernate.hbm2ddl.auto">create-drop</prop> in config hibernate then do not run script create table
- if use <prop key="hibernate.hbm2ddl.auto">none</prop> in config hibernate then have to run script create table below
-- Create table
create table ROLES
(
  ID   NUMBER(19) not null,
  CODE VARCHAR2(50 CHAR),
  NAME VARCHAR2(255 CHAR)
);
-- Create/Recreate primary, unique and foreign key constraints 
alter table ROLES add primary key (ID) using index;

-- Create table
create table USERS
(
  ID       NUMBER(19) not null,
  FULLNAME VARCHAR2(255 CHAR),
  USERNAME VARCHAR2(50 CHAR)
);
-- Create/Recreate primary, unique and foreign key constraints 
alter table USERS add primary key (ID) using index;

-- Create table
create table USER_ROLE
(
  USER_ID NUMBER(19) not null,
  ROLE_ID NUMBER(19) not null
);
-- Create/Recreate primary, unique and foreign key constraints 
alter table USER_ROLE add primary key (USER_ID, ROLE_ID) using index;
alter table USER_ROLE add constraint FKBC16F46AA154824E foreign key (USER_ID) references USERS (ID);
alter table USER_ROLE add constraint FKBC16F46AFBFF2A78 foreign key (ROLE_ID) references ROLES (ID);

insert into roles (ID, CODE, NAME) values (1, 'A01', 'admin');
insert into roles (ID, CODE, NAME) values (2, 'A02', 'user');
commit;

