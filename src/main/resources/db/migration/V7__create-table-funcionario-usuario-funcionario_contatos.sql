create table usuario(
    id bigint not null auto_increment,
    login varchar(150) not null unique,
    senha varchar(255) not null,
    role_id bigint,
    constraint usuariopk primary key(id)
);


create table funcionario(
    id bigint not null auto_increment,
    nome varchar(150) not null,
    idade int not null,
    cpf varchar(11) not null unique,
    rg varchar(12) not null unique,
    orgao_emissor varchar(50),
    genero varchar(9) not null,
    data_de_nascimento date not null,
    data_de_admissao date not null,
    estado_civil varchar(10) not null,
    cargo varchar(100) not null,
    remuneracao numeric(7, 2) not null,
    jornada_de_trabalho varchar(300) not null,
    endereco_id bigint,
    habilitado boolean,
    usuario_id bigint,
    constraint funcionariopk primary key(id),
    constraint funcionario_enderecofk foreign key(endereco_id) references endereco(id),
    constraint funcionario_usuariofk foreign key(usuario_id) references usuario(id)
);

create table funcionario_contatos(
    contatos_id bigint,
    funcionario_id bigint,
    constraint funcionario_contatos_contatofk foreign key(contatos_id) references contato(id),
    constraint funcionario_contatos_funcionario_fk foreign key(funcionario_id) references funcionario(id)
);






