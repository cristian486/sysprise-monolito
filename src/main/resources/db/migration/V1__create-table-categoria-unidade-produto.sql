create table unidade(
    id bigint not null auto_increment,
    nome varchar(50) not null unique,
    abreviacao varchar(15),
    constraint unidadepk primary key(id)
);

create table categoria(
    id bigint not null auto_increment,
    nome varchar(50) not null unique,
    descricao varchar(200),
    constraint categoriapk primary key(id)
);

-- OBS: NO NUMERIC PASSAMOS COMO PARÂMETRO O NÚMERO MÁXIMO DE DÍGITOS INCLUINDO AS CASAS DECIMAIS E O SEGUNDO PARÂMETRO É O NÚMERO MÁXIMO DE DÍGITOS APÓS A CASA DECIMAL

create table produto(
    id bigint not null auto_increment,
    nome varchar(200) not null unique,
    descricao varchar(300) not null,
    observacao varchar(300),
    codigo_de_barras varchar(15) not null unique,
    peso_bruto numeric(9, 4),
    peso_liquido numeric(9, 4),
    preco_de_compra numeric(8, 3) not null,
    preco_de_venda numeric(8, 3) not null,
    altura numeric(8, 3),
    largura numeric(8, 3),
    profundidade numeric(8, 3),
    estoque_minimo numeric(8, 3),
    venda_fracionada boolean,
    habilitado boolean,
    unidade_id bigint,
    categoria_id bigint,
    constraint produtopk primary key(id),
    constraint produto_unidadefk foreign key(unidade_id) references unidade(id),
    constraint produto_categoriafk foreign key(categoria_id) references categoria(id)
);