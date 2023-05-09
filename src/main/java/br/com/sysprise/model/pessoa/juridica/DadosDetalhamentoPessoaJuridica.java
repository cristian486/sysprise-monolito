package br.com.sysprise.model.pessoa.juridica;

import br.com.sysprise.model.contato.DadosListagemContato;
import br.com.sysprise.model.endereco.DadosDetalhamentoEndereco;
import br.com.sysprise.model.pessoa.DadosDetalhamentoPessoa;
import br.com.sysprise.model.tipo.DadosDetalhamentoTipo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class DadosDetalhamentoPessoaJuridica extends DadosDetalhamentoPessoa {
    private Long id;
    private String razaoSocial;
    private String nomeFantasia;
    private String cnpj;

    public DadosDetalhamentoPessoaJuridica(PessoaJuridica pessoaJuridica) {
        super(new DadosDetalhamentoEndereco(pessoaJuridica.getEndereco()),
                pessoaJuridica.getTipos().stream().map(DadosDetalhamentoTipo::new).toList(),
                pessoaJuridica.getContatos().stream().map(DadosListagemContato::new).toList());
        this.id = pessoaJuridica.getId();
        this.razaoSocial = pessoaJuridica.getRazaoSocial();
        this.nomeFantasia = pessoaJuridica.getNomeFantasia();
        this.cnpj = pessoaJuridica.getCnpj();
    }
}
