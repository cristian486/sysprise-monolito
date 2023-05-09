package br.com.sysprise.model.funcionario;

import br.com.sysprise.model.cidade.Cidade;
import br.com.sysprise.model.contato.Contato;
import br.com.sysprise.model.endereco.DadosAtualizarEndereco;
import br.com.sysprise.model.endereco.Endereco;
import br.com.sysprise.model.funcionario.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "cpf")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer idade;
    private String cpf;
    private String rg;
    private String orgaoEmissor;
    @Enumerated(EnumType.STRING)
    private Genero genero;
    private LocalDate dataDeNascimento;
    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;
    private String cargo;
    private BigDecimal remuneracao;
    private LocalDate dataDeAdmissao;
    private String jornadaDeTrabalho;
    private Boolean habilitado;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Endereco endereco;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Contato> contatos = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Usuario usuario;

    public Funcionario(DadosCadastroFuncionario dadosCadastro, Cidade cidade) {
        this.nome = dadosCadastro.nome();
        this.idade = dadosCadastro.idade();
        this.cpf = dadosCadastro.cpf();
        this.rg = dadosCadastro.rg();
        this.orgaoEmissor = dadosCadastro.orgaoEmissor();
        this.genero = dadosCadastro.genero();
        this.dataDeNascimento = dadosCadastro.dataDeNascimento();
        this.estadoCivil = dadosCadastro.estadoCivil();
        this.cargo = dadosCadastro.cargo();
        this.remuneracao = dadosCadastro.remuneracao();
        this.dataDeAdmissao = LocalDate.now();
        this.jornadaDeTrabalho = dadosCadastro.jornadaDeTrabalho();
        this.habilitado = Boolean.TRUE;
        this.endereco = new Endereco(dadosCadastro.endereco(), cidade);
        this.contatos = dadosCadastro.contatos().stream().map(Contato::new).toList();
    }

    public void atualizarCadastro(DadosAtualizarFuncionario dadosAtualizar) {
        if(dadosAtualizar.nome() != null && !dadosAtualizar.nome().isEmpty())
            this.nome = dadosAtualizar.nome();

        if(dadosAtualizar.estadoCivil() != null)
            this.estadoCivil = dadosAtualizar.estadoCivil();

        if(dadosAtualizar.cargo() != null && !dadosAtualizar.cargo().isEmpty())
            this.cargo = dadosAtualizar.cargo();

        if(dadosAtualizar.remuneracao() != null && dadosAtualizar.remuneracao().subtract(this.remuneracao).doubleValue() > 0)
            this.remuneracao = dadosAtualizar.remuneracao();

        if(dadosAtualizar.jornadaDeTrabalho() != null && !dadosAtualizar.jornadaDeTrabalho().isEmpty())
            this.jornadaDeTrabalho = dadosAtualizar.jornadaDeTrabalho();
    }

    public void atualizarCadastro(DadosAtualizarEndereco dadosAtualizar, Cidade cidade) {
        this.endereco.atualizarCadastro(dadosAtualizar, cidade);
    }

    public void desabilitarCadastro() {
        this.habilitado = Boolean.FALSE;
    }
}
