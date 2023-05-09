package br.com.sysprise.service;

import br.com.sysprise.model.compra.Compra;
import br.com.sysprise.model.compra.DadosCadastroCompra;
import br.com.sysprise.model.compra.itemcompra.ItemCompra;
import br.com.sysprise.model.pessoa.Pessoa;
import br.com.sysprise.model.produto.Produto;
import br.com.sysprise.service.pessoa.PessoaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GerarCompra {

    private final PessoaService pessoaService;
    private final ProdutoService produtoService;

    public Compra executar(DadosCadastroCompra dadosCadastro) {
        Pessoa pessoa = pessoaService.findPessoaById(dadosCadastro.pessoa_id());
        Compra compra = new Compra(dadosCadastro, pessoa);
        dadosCadastro.itens().forEach(itemCompraCadastro -> {
            Produto produto = produtoService.findProdutoById(itemCompraCadastro.produto_id());
            ItemCompra itemCompra = new ItemCompra(null, compra, produto, itemCompraCadastro.quantidade());
            compra.adicionarItem(itemCompra);
        });
        return compra;
    }
}
