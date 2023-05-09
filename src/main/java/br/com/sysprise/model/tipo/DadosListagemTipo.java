package br.com.sysprise.model.tipo;

public record DadosListagemTipo(Long id, String nome) {

    public DadosListagemTipo(Tipo tipo) {
        this(tipo.getId(), tipo.getNome());
    }
}
