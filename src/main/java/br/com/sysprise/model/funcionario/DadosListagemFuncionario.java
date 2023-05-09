package br.com.sysprise.model.funcionario;

public record DadosListagemFuncionario(Long id, String nome, String cargo, Genero genero) {

    public DadosListagemFuncionario(Funcionario funcionario) {
        this(funcionario.getId(), funcionario.getNome(), funcionario.getCargo(), funcionario.getGenero());
    }
}
