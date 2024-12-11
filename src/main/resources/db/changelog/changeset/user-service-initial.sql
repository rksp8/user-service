create table users
(
    id  long auto_increment not null,
    username   varchar(255) not null,
    password   varchar(255) not null,
    role       varchar(255) not null,
    provider   varchar(255) not null,
    created_at timestamp    not null,
    constraint pk_users primary key (id)
);