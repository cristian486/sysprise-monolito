insert into role(nome) values('ROLE_ADMIN');
insert into role(nome) values('ROLE_MANAGER');
insert into role(nome) values('ROLE_USER');
insert into role(nome) values('ROLE_GUEST');

insert into usuario(login, senha, role_id) values('admin', '$2y$10$tQDQHoqB0cXTnP.JgO9wduTSQMIBdSdS8IfSq6Q4y5sOidpXGHGw6', (select id from role where nome = 'ROLE_ADMIN'));

insert into funcionario(nome, idade, cpf, rg, genero, data_de_nascimento, data_de_admissao, estado_civil, cargo, remuneracao, jornada_de_trabalho, habilitado, usuario_id, orgao_emissor) values('Administrador', 100, '00011122233', '000111222333', 'MASCULINO', '1970-01-01', '2000-01-01', 'SOLTEIRO', 'ADMINISTRADOR', 99999.99, 'NENHUMA', 1, (select id from usuario where login = 'admin'),  'SSP/SP');