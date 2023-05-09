package br.com.sysprise.service;

import br.com.sysprise.infra.exception.ValidacaoException;
import br.com.sysprise.model.categoria.*;
import br.com.sysprise.repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public Categoria findCategoriaById(Long id) {
        return categoriaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("A categoria requisitada não foi encontrada!"));
    }

    public Page<DadosListagemCategoria> listar(Pageable pageable) {
        return categoriaRepository.findAll(pageable).map(DadosListagemCategoria::new);
    }

    public DadosDetalhamentoCategoria detalhar(Long id) {
        Categoria categoria = this.findCategoriaById(id);
        return new DadosDetalhamentoCategoria(categoria);
    }

    public DadosDetalhamentoCategoria cadastrar(DadosCadastroCategoria dadosCadastro) {
        Categoria categoria = new Categoria(dadosCadastro);
        categoriaRepository.save(categoria);
        return new DadosDetalhamentoCategoria(categoria);
    }

    public DadosDetalhamentoCategoria atualizar(DadosAtualizarCategoria dadosAtualizar) {
        Categoria categoria = this.findCategoriaById(dadosAtualizar.id());
        categoria.atualizarCadastro(dadosAtualizar);
        return new DadosDetalhamentoCategoria(categoria);
    }

    public void deletar(Long id) {
        Categoria categoria = this.findCategoriaById(id);
        Boolean haProdutosVinculados = categoriaRepository.haProdutosVinculadosCategoria(categoria.getId());
        if(haProdutosVinculados) throw new ValidacaoException("Categoria não pode ser deletada pois há produtos vinculados a ela");
        categoriaRepository.delete(categoria);
    }
}
