create schema if not exists gateway;
use gateway;
create table if not exists user
(
    id          bigint  not null auto_increment,
    username   varchar(255),
    hash       varchar(255),
    primary key (id)
);
