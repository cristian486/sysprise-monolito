create table role(
    id bigint not null auto_increment,
    nome varchar(100) not null unique,
    constraint rolepk primary key(id)
);