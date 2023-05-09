package br.com.sysprise.model.funcionario.usuario;

public record DadosListagemUsuario(Long id, String login, String senha) {

    public DadosListagemUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getLogin(), usuario.getSenha());
    }
}
