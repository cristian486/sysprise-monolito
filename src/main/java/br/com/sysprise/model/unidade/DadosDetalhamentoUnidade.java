package br.com.sysprise.model.unidade;

public record DadosDetalhamentoUnidade(Long id, String nome, String abreviacao) {

    public DadosDetalhamentoUnidade(Unidade unidade) {
        this(unidade.getId(), unidade.getNome(), unidade.getAbreviacao());
    }
}
