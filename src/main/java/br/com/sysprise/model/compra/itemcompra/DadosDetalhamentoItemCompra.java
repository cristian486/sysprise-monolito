package br.com.sysprise.model.compra.itemcompra;

import br.com.sysprise.model.produto.DadosDetalhamentoProduto;

public record DadosDetalhamentoItemCompra(Long id, DadosDetalhamentoProduto produto, Double quantidade) {

    public DadosDetalhamentoItemCompra(ItemCompra itemCompra) {
        this(itemCompra.getId(), new DadosDetalhamentoProduto(itemCompra.getProduto()), itemCompra.getQuantidade());
    }
}
