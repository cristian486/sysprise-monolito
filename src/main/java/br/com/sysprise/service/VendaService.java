package br.com.sysprise.service;

import br.com.sysprise.model.venda.*;
import br.com.sysprise.repository.VendaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VendaService {



    private final GerarVenda gerarVenda;
    private final VendaRepository vendaRepository;
    private final AtualizarItemVendaService atualizarItemVendaService;

    public Venda findVendaById(Long id) {
        return vendaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("A venda solicitada não foi encontrada!"));
    }

    public Page<DadosListagemVenda> listar(Pageable pageable){
        return vendaRepository.findAllByHabilitadoTrue(pageable).map(DadosListagemVenda::new);
    }

    public DadosDetalhamentoVenda detalhar(Long id) {
        Venda venda = this.findVendaById(id);
        return new DadosDetalhamentoVenda(venda);
    }

    public DadosDetalhamentoVenda cadastrar(DadosCadastroVenda dadosCadastro) {
        Venda venda = gerarVenda.executar(dadosCadastro);
        vendaRepository.save(venda);
        return new DadosDetalhamentoVenda(venda);
    }

    public void atualizarStatus(DadosAtualizarStatusVenda dadosAtualizar) {
        Venda venda = this.findVendaById(dadosAtualizar.id());

        if(!dadosAtualizar.aprovar()) {
           venda.cancelar();
           return;
        }

        venda.aprovar();
    }

    public DadosDetalhamentoVenda atualizar(DadosAtualizarVenda dadosAtualizar) {
        Venda venda = this.findVendaById(dadosAtualizar.id());
        venda.atualizarCadastro(dadosAtualizar);
        dadosAtualizar.itens().ifPresent(itensAtualizar -> atualizarItemVendaService.executar(itensAtualizar, venda));
        return new DadosDetalhamentoVenda(venda);
    }


    public void deletar(Long id) {
        Venda venda = this.findVendaById(id);
        String statusDoPedido = venda.getStatus().toString();
        String statusAberto = StatusVenda.ABERTO.toString();
        if(!statusDoPedido.equals(statusAberto)) {
            venda.desabilitarCadastro();
            return;
        }

        vendaRepository.delete(venda);
    }
}
