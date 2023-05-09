package br.com.sysprise.model.compra;

import br.com.sysprise.model.compra.itemcompra.DadosDetalhamentoItemCompra;
import br.com.sysprise.model.pessoa.DadosDetalhamentoPessoa;
import br.com.sysprise.model.pessoa.Pessoa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record DadosDetalhamentoCompra(Long id, String documento, String observacao, LocalDateTime dataDeCriacao, LocalDate dataDeRecebimento,
                                      String status, DadosDetalhamentoPessoa fornecedor,
                                      List<DadosDetalhamentoItemCompra> itensDaCompra) {

    public DadosDetalhamentoCompra(Compra compra) {
        this(compra.getId(), compra.getDocumento(), compra.getObservacao(), compra.getDataDeCriacao(), compra.getDataDeRecebimento(),
                compra.getStatus().toString(), Pessoa.converterPessoaParaDto(compra.getFornecedor()),
                compra.getItensDaCompra().stream().map(DadosDetalhamentoItemCompra::new).toList());
    }
}
