package br.com.sysprise.model.unidade;

public record DadosListagemUnidade(Long id, String nome) {

    public DadosListagemUnidade(Unidade unidade) {
        this(unidade.getId(), unidade.getNome());
    }
}
