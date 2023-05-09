package br.com.sysprise.model.venda;

import br.com.sysprise.model.pessoa.DadosDetalhamentoPessoa;
import br.com.sysprise.model.pessoa.Pessoa;
import br.com.sysprise.model.venda.itemvenda.DadosDetalhamentoItemVenda;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record DadosDetalhamentoVenda(Long id, String documento, String observacao, LocalDateTime dataDeCriacao, LocalDate dataDeEntrega,
                                     String status, Double desconto, BigDecimal valorTotal,
                                     DadosDetalhamentoPessoa pessoa, List<DadosDetalhamentoItemVenda> itensDaVenda) {

    public DadosDetalhamentoVenda(Venda venda) {
        this(venda.getId(), venda.getDocumento(), venda.getObservacao(), venda.getDataDeCriacao(), venda.getDataDeEntrega(),
                venda.getStatus().toString(), venda.getDesconto(), venda.getValorTotal(),
                Pessoa.converterPessoaParaDto(venda.getCliente()),
                venda.getItensDaVenda().stream().map(DadosDetalhamentoItemVenda::new).toList());
    }
}
