package br.com.sysprise.service;

import br.com.sysprise.infra.exception.ValidacaoException;
import br.com.sysprise.model.unidade.*;
import br.com.sysprise.repository.UnidadeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UnidadeService {

    private final UnidadeRepository unidadeRepository;

    public Unidade findUnidadeById(Long id) {
        return unidadeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("A unidade requisitada não foi encontrada!"));
    }

    public Page<DadosListagemUnidade> listar(Pageable pageable) {
        return unidadeRepository.findAll(pageable).map(DadosListagemUnidade::new);
    }

    public DadosDetalhamentoUnidade detalhar(Long id) {
        Unidade unidade = this.findUnidadeById(id);
        return new DadosDetalhamentoUnidade(unidade);
    }

    public DadosDetalhamentoUnidade cadastrar(DadosCadastroUnidade dadosCadastro) {
        Unidade unidade = new Unidade(dadosCadastro);
        unidadeRepository.save(unidade);
        return new DadosDetalhamentoUnidade(unidade);
    }

    public DadosDetalhamentoUnidade atualizar(DadosAtualizarUnidade dadosAtualizar) {
        Unidade unidade = this.findUnidadeById(dadosAtualizar.id());
        unidade.atualizarCadastro(dadosAtualizar);
        return new DadosDetalhamentoUnidade(unidade);
    }

    public void deletar(Long id) {
        Unidade unidade = this.findUnidadeById(id);
        Boolean haProdutosVinculados = unidadeRepository.haProdutosVinculadosUnidade(unidade.getId());
        if(haProdutosVinculados) throw new ValidacaoException("Unidade não pode ser deletada pois há produtos vinculados a ela");
        unidadeRepository.delete(unidade);
    }
}
