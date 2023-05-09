package br.com.sysprise.model.estado;

public record DadosListagemEstado(Long id, String nome, String sigla) {

    public DadosListagemEstado(Estado estado) {
        this(estado.getId(), estado.getNome(), estado.getSigla());
    }
}
