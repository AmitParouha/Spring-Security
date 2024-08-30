create table users(
	id int NOT NULL auto_increment primary key,
	username varchar(50) not null unique,
	password varchar(500) not null,
    enabled boolean not null
);

create table authorities (
	id int NOT NULL auto_increment primary key,
	username varchar(50) not null,
    authority varchar(50) not null
);

insert into  users values(1, "amit07@gmail.com", "amit1234", "1");
insert into  authorities values(1, "amit07@gmail.com", "write");

create unique index ix_auth_username on authorities (username,authority);
