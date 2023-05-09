create table pessoa(
    id bigint not null auto_increment,
    habilitado boolean,
    endereco_id bigint,
    constraint pessoapk primary key(id),
    constraint pessoa_endereco_fk foreign key(endereco_id) references endereco(id)
);


create table pessoa_juridica(
    id bigint not null,
    razao_social varchar(200) not null unique,
    nome_fantasia varchar(200),
    cnpj char(14) not null unique
);

/*

The DATE type is used for values with a date part but no time part. MySQL retrieves and displays DATE values in 'YYYY-MM-DD' format. The supported range is '1000-01-01' to '9999-12-31'.

The DATETIME type is used for values that contain both date and time parts. MySQL retrieves and displays DATETIME values in 'YYYY-MM-DD hh:mm:ss' format. The supported range is '1000-01-01 00:00:00' to '9999-12-31 23:59:59'.

The TIMESTAMP data type is used for values that contain both date and time parts. TIMESTAMP has a range of '1970-01-01 00:00:01' UTC to '2038-01-19 03:14:07' UTC.

*/

create table pessoa_fisica(
    id bigint not null,
    nome varchar(200) not null unique,
    cpf char(11) not null unique,
    genero varchar(9) not null,
    data_de_nascimento date not null
);

create table pessoa_tipos(
    tipos_id bigint,
    pessoa_id bigint,
    constraint pessoa_tipos_tipofk foreign key(tipos_id) references tipo(id),
    constraint pessoa_tipos_pessoa_fk foreign key(pessoa_id) references pessoa(id)
);

create table pessoa_contatos(
    contatos_id bigint,
    pessoa_id bigint,
    constraint pessoa_contatos_contatofk foreign key(contatos_id) references contato(id),
    constraint pessoa_contatos_pessoa_fk foreign key(pessoa_id) references pessoa(id)
);

