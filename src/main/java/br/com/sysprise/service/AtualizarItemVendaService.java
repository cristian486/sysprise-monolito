package br.com.sysprise.service;

import br.com.sysprise.model.venda.Venda;
import br.com.sysprise.model.venda.itemvenda.DadosAtualizarItemVenda;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AtualizarItemVendaService {

    public void executar(List<DadosAtualizarItemVenda> itens, Venda venda) {

        itens.forEach(itemParaAtualizar -> {
            itemParaAtualizar.id().ifPresent(id -> {
                venda.buscarItemVendaPorId(id).ifPresent(itemCadastrado -> {
                    if(itemParaAtualizar.deveRemover())
                        venda.removerItem(itemCadastrado);
                    else
                        itemCadastrado.atualizarCadastro(itemParaAtualizar);
                });
            });
        });
    }
}
