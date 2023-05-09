package br.com.sysprise.model.funcionario;

import br.com.sysprise.model.contato.DadosListagemContato;
import br.com.sysprise.model.endereco.DadosDetalhamentoEndereco;
import br.com.sysprise.model.funcionario.usuario.DadosListagemUsuario;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record DadosDetalhamentoFuncionario(Long id, String nome, Integer idade, String cpf, String rg, String orgaoEmissor,
                                           Genero genero, LocalDate dataDeNascimento, EstadoCivil estadoCivil, String cargo,
                                           BigDecimal remuneracao, LocalDate dataDeAdmissao, String jornadaDeTrabalho,
                                           DadosDetalhamentoEndereco endereco,
                                           List<DadosListagemContato> contatos, DadosListagemUsuario usuario) {

    public DadosDetalhamentoFuncionario(Funcionario funcionario) {
        this(funcionario.getId(), funcionario.getNome(), funcionario.getIdade(), funcionario.getCpf(),
                funcionario.getRg(), funcionario.getOrgaoEmissor(), funcionario.getGenero(), funcionario.getDataDeNascimento(),
                funcionario.getEstadoCivil(), funcionario.getCargo(), funcionario.getRemuneracao(), funcionario.getDataDeAdmissao(),
                funcionario.getJornadaDeTrabalho(), new DadosDetalhamentoEndereco(funcionario.getEndereco()),
                funcionario.getContatos().stream().map(DadosListagemContato::new).toList(), funcionario.getUsuario() != null ? new DadosListagemUsuario(funcionario.getUsuario()) : null);
    }
}
