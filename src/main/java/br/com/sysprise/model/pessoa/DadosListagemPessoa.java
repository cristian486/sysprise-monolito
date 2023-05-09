package br.com.sysprise.model.pessoa;

import lombok.Getter;

@Getter
public class DadosListagemPessoa {
    protected Long id;
    protected String nome;

    public DadosListagemPessoa(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
