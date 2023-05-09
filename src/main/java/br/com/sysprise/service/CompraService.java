package br.com.sysprise.service;

import br.com.sysprise.model.compra.*;
import br.com.sysprise.repository.CompraRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompraService {

    private final GerarCompra gerarCompra;
    private final CompraRepository compraRepository;
    private final AtualizarItemCompraService atualizarItemCompraService;

    public Compra findCompraById(Long id) {
        return compraRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("A compra requisitada não foi encontrada"));
    }

    public Page<DadosListagemCompra> listar(Pageable pageable) {
        return compraRepository.findAllByHabilitadoTrue(pageable).map(DadosListagemCompra::new);
    }

    public DadosDetalhamentoCompra detalhar(Long id) {
        Compra compra = this.findCompraById(id);
        return new DadosDetalhamentoCompra(compra);
    }

    public DadosDetalhamentoCompra cadastrar(DadosCadastroCompra dadosCadastro) {
        Compra compra = gerarCompra.executar(dadosCadastro);
        compraRepository.save(compra);
        return new DadosDetalhamentoCompra(compra);
    }

    public DadosDetalhamentoCompra atualizar(DadosAtualizarCompra dadosAtualizar) {
        Compra compra = this.findCompraById(dadosAtualizar.id());
        compra.atualizarCadastro(dadosAtualizar);
        dadosAtualizar.itens().ifPresent(itensAtualizar -> atualizarItemCompraService.executar(itensAtualizar, compra));
        return new DadosDetalhamentoCompra(compra);
    }

    public void deletar(Long id) {
        Compra compra = this.findCompraById(id);
        String statusInicial = StatusCompra.AGUARDANDO_APROVACAO.name();
        if(!compra.getStatus().name().equals(statusInicial)) {
            compra.desabilitarCadastro();
            return;
        }

        compraRepository.delete(compra);
    }
}
