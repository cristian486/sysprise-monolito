package br.com.sysprise.model.contato;

public record DadosListagemContato(Long id, String email, String telefone) {

    public DadosListagemContato(Contato contato) {
        this(contato.getId(), contato.getEmail(), contato.getTelefone());
    }
}
