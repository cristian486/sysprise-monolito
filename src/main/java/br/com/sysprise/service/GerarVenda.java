package br.com.sysprise.service;

import br.com.sysprise.model.pessoa.Pessoa;
import br.com.sysprise.model.produto.Produto;
import br.com.sysprise.model.venda.DadosCadastroVenda;
import br.com.sysprise.model.venda.DadosDetalhamentoVenda;
import br.com.sysprise.model.venda.Venda;
import br.com.sysprise.model.venda.itemvenda.DadosCadastroItemVenda;
import br.com.sysprise.model.venda.itemvenda.ItemVenda;
import br.com.sysprise.service.pessoa.PessoaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class GerarVenda {

    private final ProdutoService produtoService;
    private final PessoaService pessoaService;


    public Venda executar(DadosCadastroVenda dadosCadastro) {
        Pessoa pessoa = pessoaService.findPessoaById(dadosCadastro.pessoa_id());
        Venda venda = new Venda(dadosCadastro, pessoa);

        dadosCadastro.itens().stream().forEach(cadastroItemVenda -> {
            Produto produto = produtoService.findProdutoById(cadastroItemVenda.produto_id());
            ItemVenda itemVenda =  new ItemVenda(cadastroItemVenda.quantidade(), produto, venda);
            venda.adicionarItem(itemVenda);
        });

        return venda;
    }


}
