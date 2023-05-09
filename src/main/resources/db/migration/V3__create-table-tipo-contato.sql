create table contato(
    id bigint not null auto_increment,
    email varchar(100),
    telefone varchar(13),
    constraint contatopk primary key(id)
);

create table tipo(
    id bigint not null auto_increment,
    nome varchar(50) not null unique,
    constraint tipopk primary key(id)
);
