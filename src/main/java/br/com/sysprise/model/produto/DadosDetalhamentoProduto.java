package br.com.sysprise.model.produto;

import br.com.sysprise.model.categoria.DadosDetalhamentoCategoria;
import br.com.sysprise.model.unidade.DadosDetalhamentoUnidade;

import java.math.BigDecimal;

public record DadosDetalhamentoProduto(Long id, String nome, String descricao, String observacao, String codigoDeBarras,
                                       Double pesoBruto, Double pesoLiquido, BigDecimal precoDeCompra, BigDecimal precoDeVenda,
                                       Double altura, Double largura, Double profundidade, Double estoqueMinimo, Boolean vendaFracionada,
                                       DadosDetalhamentoCategoria categoria, DadosDetalhamentoUnidade unidade) {

    public DadosDetalhamentoProduto(Produto produto) {
        this(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getObservacao(), produto.getCodigoDeBarras(),
                produto.getPesoBruto(), produto.getPesoLiquido(), produto.getPrecoDeCompra(), produto.getPrecoDeVenda(),
                produto.getAltura(), produto.getLargura(), produto.getProfundidade(), produto.getEstoqueMinimo(), produto.getVendaFracionada(),
                new DadosDetalhamentoCategoria(produto.getCategoria()),
                new DadosDetalhamentoUnidade(produto.getUnidade()));
    }
}
