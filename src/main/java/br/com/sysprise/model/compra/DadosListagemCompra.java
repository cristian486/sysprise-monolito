package br.com.sysprise.model.compra;

import java.time.LocalDate;

public record DadosListagemCompra(Long id, String nomeFornecedor, LocalDate dataDeRecebimento, String observacao, String status) {

    public DadosListagemCompra(Compra compra) {
        this(compra.getId(), compra.getFornecedor().getNome(), compra.getDataDeRecebimento(),
                compra.getObservacao(), compra.getStatus().toString());
    }
}
