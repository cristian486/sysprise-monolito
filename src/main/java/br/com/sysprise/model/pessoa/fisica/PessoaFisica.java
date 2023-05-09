package br.com.sysprise.model.pessoa.fisica;

import br.com.sysprise.model.cidade.Cidade;
import br.com.sysprise.model.contato.Contato;
import br.com.sysprise.model.endereco.Endereco;
import br.com.sysprise.model.pessoa.Genero;
import br.com.sysprise.model.pessoa.Pessoa;
import br.com.sysprise.model.tipo.Tipo;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "cpf")
public class PessoaFisica extends Pessoa {

    private String nome;
    private String cpf;
    @Enumerated(EnumType.STRING)
    private Genero genero;
    private LocalDate dataDeNascimento;

    public PessoaFisica(DadosCadastroPessoaFisica dadosCadastro, List<Tipo> tipos, Cidade cidade) {
        this.nome = dadosCadastro.nome();
        this.cpf = dadosCadastro.cpf();
        this.genero = dadosCadastro.genero();
        this.dataDeNascimento = dadosCadastro.dataDeNascimento();
        this.habilitado = Boolean.TRUE;
        this.tipos = new HashSet<>(tipos);
        this.endereco = new Endereco(dadosCadastro.endereco(), cidade);
        this.contatos = dadosCadastro.contatos().stream().map(Contato::new).toList();
    }

    public void atualizarCadastro(DadosAtualizarPessoaFisica dadosAtualizar) {
        if(dadosAtualizar.getNome() != null && !dadosAtualizar.getNome().isEmpty())
            this.nome = dadosAtualizar.getNome();

        if(dadosAtualizar.getCpf() != null && !dadosAtualizar.getCpf().isEmpty())
            this.cpf = dadosAtualizar.getCpf();

        if(dadosAtualizar.getGenero() != null && !this.genero.equals(dadosAtualizar.getGenero()))
            this.genero = dadosAtualizar.getGenero();

        if(dadosAtualizar.getDataDeNascimento() != null && !this.dataDeNascimento.equals(dadosAtualizar.getDataDeNascimento()))
            this.dataDeNascimento = dadosAtualizar.getDataDeNascimento();
    }
}
