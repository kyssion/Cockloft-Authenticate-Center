create schema if not exists UserAuthentication collate latin1_swedish_ci;

create table if not exists client
(
	id bigint auto_increment comment 'default id',
	userId varchar(30) default '' not null comment 'The creator of this record',
	clientId varchar(20) default '' not null comment 'save the client id information'
		primary key,
	authUrl varchar(100) default '' not null comment 'save the client can authentication and authorization url',
	permissions int default 0 not null comment 'The permissions of this client.This permission can use the following values,0. no permission,1. Authorized certification',
	status int default 1 not null comment 'Whether this record is effective',
	createDate bigint default 0 not null comment 'The time this record was created',
	updateTime bigint default 0 not null comment 'The time this record was modified',
	constraint client_id_uindex
		unique (id)
)
comment 'save authentication center client url information';

create table if not exists user
(
	id bigint auto_increment comment 'default key',
	userId varchar(30) default '' not null comment 'the user''s id'
		primary key,
	password varchar(50) default '' not null comment 'the password hash information',
	email varchar(50) default '' null comment 'the user email information',
	tel varchar(50) default '' null comment 'the user tel information',
	status int default 1 not null comment 'Whether this record is effective',
	createTime bigint default 0 not null comment 'the time of init user information',
	updateTime bigint default 0 null comment 'the time of perssion user information',
	constraint user_id_uindex
		unique (id)
)
comment 'user information table';

