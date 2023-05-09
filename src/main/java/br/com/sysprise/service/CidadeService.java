package br.com.sysprise.service;

import br.com.sysprise.infra.exception.ValidacaoException;
import br.com.sysprise.model.cidade.*;
import br.com.sysprise.model.estado.Estado;
import br.com.sysprise.repository.CidadeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CidadeService {

    private final EstadoService estadoService;
    private final CidadeRepository cidadeRepository;

    public Cidade findCidadeById(Long id) {
        return cidadeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("A cidade requisitada não foi encontrada!"));
    }

    public Page<DadosListagemCidade> listar(Pageable pageable) {
        return cidadeRepository.findAll(pageable).map(DadosListagemCidade::new);
    }

    public DadosDetalhamentoCidade detalhar(Long id) {
        Cidade cidade = this.findCidadeById(id);
        return new DadosDetalhamentoCidade(cidade);
    }

    public DadosDetalhamentoCidade cadastrar(DadosCadastroCidade dadosCadastro) {
        Estado estado = estadoService.findEstadoById(dadosCadastro.estado_id());
        Cidade cidade = new Cidade(dadosCadastro, estado);
        cidadeRepository.save(cidade);
        return new DadosDetalhamentoCidade(cidade);
    }

    public DadosDetalhamentoCidade atualizar(DadosAtualizarCidade dadosAtualizar) {
        Cidade cidade = this.findCidadeById(dadosAtualizar.id());
        cidade.atualizarCadastro(dadosAtualizar);
        dadosAtualizar.estado_id().ifPresent(id -> {
            Estado estado = estadoService.findEstadoById(id);
            cidade.atualizarCadastro(estado);
        });
        return new DadosDetalhamentoCidade(cidade);
    }

    public void deletar(Long id) {
        Cidade cidade = this.findCidadeById(id);
        Boolean haEnderecosVinculados = cidadeRepository.haEnderecosVinculadosCidade(cidade.getId());
        if(haEnderecosVinculados) throw new ValidacaoException("Cidade não pode ser deletada pois há endereços vinculados a ela");
        cidadeRepository.delete(cidade);
    }
}
