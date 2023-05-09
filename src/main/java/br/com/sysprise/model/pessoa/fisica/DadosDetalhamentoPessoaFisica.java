package br.com.sysprise.model.pessoa.fisica;

import br.com.sysprise.model.contato.DadosListagemContato;
import br.com.sysprise.model.endereco.DadosDetalhamentoEndereco;
import br.com.sysprise.model.pessoa.DadosDetalhamentoPessoa;
import br.com.sysprise.model.pessoa.Genero;
import br.com.sysprise.model.tipo.DadosDetalhamentoTipo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public final class DadosDetalhamentoPessoaFisica extends DadosDetalhamentoPessoa {
    private Long id;
    private String nome;
    private String cpf;
    private Genero genero;
    private LocalDate dataDeNascimento;


    public DadosDetalhamentoPessoaFisica(PessoaFisica pessoaFisica) {
        super(new DadosDetalhamentoEndereco(pessoaFisica.getEndereco()),
                pessoaFisica.getTipos().stream().map(DadosDetalhamentoTipo::new).toList(),
                pessoaFisica.getContatos().stream().map(DadosListagemContato::new).toList());
        this.id = pessoaFisica.getId();
        this.nome = pessoaFisica.getNome();
        this.cpf = pessoaFisica.getCpf();
        this.genero = pessoaFisica.getGenero();
        this.dataDeNascimento = pessoaFisica.getDataDeNascimento();
    }
}
