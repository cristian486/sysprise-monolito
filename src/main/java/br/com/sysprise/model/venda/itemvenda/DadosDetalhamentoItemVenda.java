package br.com.sysprise.model.venda.itemvenda;

import java.math.BigDecimal;

public record DadosDetalhamentoItemVenda(Long id, Long produto_id, String nomeDoProduto, Double quantidade, BigDecimal valorTotal) {

    public DadosDetalhamentoItemVenda(ItemVenda itemVenda) {
        this(itemVenda.getId(), itemVenda.getProduto().getId(), itemVenda.getProduto().getNome(),
                itemVenda.getQuantidade(), itemVenda.valorTotal());
    }
}
