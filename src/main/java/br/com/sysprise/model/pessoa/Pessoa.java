package br.com.sysprise.model.pessoa;

import br.com.sysprise.model.cidade.Cidade;
import br.com.sysprise.model.contato.Contato;
import br.com.sysprise.model.endereco.DadosAtualizarEndereco;
import br.com.sysprise.model.endereco.Endereco;
import br.com.sysprise.model.pessoa.fisica.DadosDetalhamentoPessoaFisica;
import br.com.sysprise.model.pessoa.fisica.PessoaFisica;
import br.com.sysprise.model.pessoa.juridica.DadosDetalhamentoPessoaJuridica;
import br.com.sysprise.model.pessoa.juridica.PessoaJuridica;
import br.com.sysprise.model.tipo.Tipo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@AllArgsConstructor
public abstract class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected Boolean habilitado;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    protected Endereco endereco;

    @OneToMany
    protected Set<Tipo> tipos = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    protected List<Contato> contatos = new ArrayList<>();

    public abstract String getNome();

    public static DadosDetalhamentoPessoa converterPessoaParaDto(Pessoa pessoa) {
        if(pessoa instanceof PessoaFisica)
            return new DadosDetalhamentoPessoaFisica((PessoaFisica) pessoa);

        return new DadosDetalhamentoPessoaJuridica((PessoaJuridica) pessoa);
    }

    public Optional<Contato> buscarContatoPorId(Long id) {
        return this.contatos.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    public void atualizarEndereco(DadosAtualizarEndereco dadosAtualizarEndereco, Cidade cidade) {
        this.endereco.atualizarCadastro(dadosAtualizarEndereco, cidade);
    }

    public Optional<Tipo> buscarTipoPorId(Long id) {
        return this.tipos.stream().filter(t -> t.getId().equals(id)).findFirst();
    }

    public void removerContato(Contato contato) {
        this.contatos.remove(contato);
    }

    public void adicionarContato(Contato contato) {
        this.contatos.add(contato);
    }

    public void removerTipo(Tipo tipo) {
        this.tipos.remove(tipo);
    }

    public void adicionarTipo(Tipo tipo) {
        this.tipos.add(tipo);
    }
}
