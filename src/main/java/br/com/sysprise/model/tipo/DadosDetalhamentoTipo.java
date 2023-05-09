package br.com.sysprise.model.tipo;

public record DadosDetalhamentoTipo(Long id, String nome) {

    public DadosDetalhamentoTipo(Tipo tipo) {
        this(tipo.getId(), tipo.getNome());
    }
}
