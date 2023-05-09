package br.com.sysprise.model.produto;

import br.com.sysprise.model.categoria.Categoria;
import br.com.sysprise.model.unidade.Unidade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"nome", "codigoDeBarras"})
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private String observacao;
    private String codigoDeBarras;
    private Double pesoBruto;
    private Double pesoLiquido;
    private BigDecimal precoDeCompra;
    private BigDecimal precoDeVenda;
    private Double altura;
    private Double largura;
    private Double profundidade;
    private Double estoqueMinimo;
    private Boolean vendaFracionada;
    private Boolean habilitado;
    @OneToOne(fetch = FetchType.LAZY)
    private Unidade unidade;
    @OneToOne(fetch = FetchType.LAZY)
    private Categoria categoria;

    public Produto(DadosCadastroProduto dadosCadastro, Unidade unidade, Categoria categoria) {
        this.nome = dadosCadastro.nome();
        this.descricao = dadosCadastro.descricao();
        this.observacao = dadosCadastro.observacao();
        this.codigoDeBarras = dadosCadastro.codigoDeBarras();
        this.pesoBruto = dadosCadastro.pesoBruto();
        this.pesoLiquido = dadosCadastro.pesoLiquido();
        this.precoDeCompra = dadosCadastro.precoDeCompra();
        this.precoDeVenda = dadosCadastro.precoDeVenda();
        this.altura = dadosCadastro.altura();
        this.largura = dadosCadastro.largura();
        this.profundidade = dadosCadastro.profundidade();
        this.estoqueMinimo = dadosCadastro.estoqueMinimo();
        this.vendaFracionada = dadosCadastro.vendaFracionada();
        this.unidade = unidade;
        this.categoria = categoria;
        this.habilitado = Boolean.TRUE;
    }

    public void atualizarCadastro(DadosAtualizarProduto dadosAtualizar) {
        if(dadosAtualizar.nome() != null && !dadosAtualizar.nome().isEmpty())
            this.nome = dadosAtualizar.nome();

        if(dadosAtualizar.descricao() != null && !dadosAtualizar.descricao().isEmpty())
            this.descricao = dadosAtualizar.descricao();

        if(dadosAtualizar.observacao() != null && !dadosAtualizar.observacao().isEmpty())
            this.observacao = dadosAtualizar.observacao();

        if(dadosAtualizar.codigoDeBarras() != null && !dadosAtualizar.codigoDeBarras().isEmpty())
            this.codigoDeBarras = dadosAtualizar.codigoDeBarras();

        if(dadosAtualizar.pesoBruto() != null && dadosAtualizar.pesoBruto().doubleValue() > 0)
            this.pesoBruto = dadosAtualizar.pesoBruto();

        if(dadosAtualizar.pesoLiquido() != null && dadosAtualizar.pesoLiquido().doubleValue() > 0)
            this.pesoLiquido = dadosAtualizar.pesoLiquido();

        if(dadosAtualizar.precoDeCompra() != null)
            this.precoDeCompra = dadosAtualizar.precoDeCompra();

        if(dadosAtualizar.precoDeVenda() != null)
            this.precoDeVenda = dadosAtualizar.precoDeVenda();

        if(dadosAtualizar.altura() != null && dadosAtualizar.altura().doubleValue() > 0)
            this.altura = dadosAtualizar.altura();

        if(dadosAtualizar.largura() != null && dadosAtualizar.largura().doubleValue() > 0)
            this.largura = dadosAtualizar.largura();

        if(dadosAtualizar.profundidade() != null && dadosAtualizar.profundidade().doubleValue() > 0)
            this.profundidade = dadosAtualizar.profundidade();

        if(dadosAtualizar.estoqueMinimo() != null && dadosAtualizar.estoqueMinimo().doubleValue() > 0)
            this.estoqueMinimo = dadosAtualizar.estoqueMinimo();

        if(dadosAtualizar.vendaFracionada() != null && dadosAtualizar.vendaFracionada())
            this.vendaFracionada = dadosAtualizar.vendaFracionada();
    }

    public void atualizarCadastro(Categoria categoria) {
        if(!this.categoria.getId().equals(categoria.getId()))
            this.categoria = categoria;
    }

    public void atualizarCadastro(Unidade unidade) {
        if(!this.unidade.getId().equals(unidade.getId()))
            this.unidade = unidade;
    }

    public Boolean permitidaVendaFracionada() {
        return this.vendaFracionada;
    }

    public void desabilitarCadastro() {
        this.habilitado = Boolean.FALSE;
    }
}
