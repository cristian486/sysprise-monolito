<h1 align="center"> Sysprise </h1>

<p align="center">
    <img src="http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=GREEN&style=for-the-badge"/>
</p>

## Índice

* [Descrição do Projeto](#descrição-do-projeto)
* [Status do Projeto](#status-do-Projeto)
* [Funcionalidades e Demonstração da Aplicação](#funcionalidades-e-demonstração-da-aplicação)
* [Tecnologias utilizadas](#tecnologias-utilizadas)
* [Licença](#licença)
* [Conclusão](#conclusão)

## Descrição do Projeto

Aplicação em desenvolvimento que tem o objetivo de fornecer uma API com os recursos essenciais para um sistema ERP, construído com Spring Boot.

Atualmente o projeto esta sendo movido para uma arquitetura distribuída, onde serão implementadas tecnologias como `gRPC`, `IC`, `KeyCloak` e `RabbitMq`.

Partes do monólito que foram movidas para projetos/repositórios distintos:

* [Produto](https://github.com/cristian486/sysprise-produto)

* [Categoria](https://github.com/cristian486/sysprise-produto)

* [Unidade](https://github.com/cristian486/sysprise-produto)

## Status do Projeto

:construction: Projeto em construção :construction:

## Funcionalidades

Atualmente o sistema possibilita o CRUD de pessoa física e jurídica, produto, funcionário, usuário, cidade, tipo de pessoa, cidade, estado, endereço, contatos, unidade, venda e compra.

## Tecnologias utilizadas

- ``Java``
- ``Spring Boot``
- ``Spring Data JPA``
- ``Spring Security``
- ``Spring MVC``
- ``Flyway``
- ``MySql``
- ``Spring Doc``
- ``Lombok``
- ``Docker``