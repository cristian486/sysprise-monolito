package br.com.sysprise.model.venda;

import java.time.LocalDate;

public record DadosListagemVenda(Long id, String nomeCliente, LocalDate dataDeEntrega, String observacao, String status) {

    public DadosListagemVenda(Venda venda) {
        this(venda.getId(), venda.getCliente().getNome(), venda.getDataDeEntrega(), venda.getObservacao(), venda.getStatus().toString());
    }
}
