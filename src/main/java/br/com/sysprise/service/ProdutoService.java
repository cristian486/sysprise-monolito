package br.com.sysprise.service;

import br.com.sysprise.model.categoria.Categoria;
import br.com.sysprise.model.produto.*;
import br.com.sysprise.model.unidade.Unidade;
import br.com.sysprise.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProdutoService {

    private final UnidadeService unidadeService;
    private final CategoriaService categoriaService;
    private final ProdutoRepository produtoRepository;


    public Produto findProdutoById(Long id) {
        return produtoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("O produto requisitado não foi encontrado!"));
    }

    public Page<DadosListagemProduto> listar(Pageable pageable) {
        return produtoRepository.findProdutoByHabilitadoTrue(pageable).map(DadosListagemProduto::new);
    }

    public DadosDetalhamentoProduto detalhar(Long id) {
        Produto produto = this.findProdutoById(id);
        return new DadosDetalhamentoProduto(produto);
    }

    public DadosDetalhamentoProduto cadastrar(DadosCadastroProduto dadosCadastro) {
        Unidade unidade = unidadeService.findUnidadeById(dadosCadastro.unidade_id());
        Categoria categoria = categoriaService.findCategoriaById(dadosCadastro.categoria_id());
        Produto produto = new Produto(dadosCadastro, unidade, categoria);
        produtoRepository.save(produto);
        return new DadosDetalhamentoProduto(produto);
    }

    public DadosDetalhamentoProduto atualizar(DadosAtualizarProduto dadosAtualizar) {
        Produto produto = this.findProdutoById(dadosAtualizar.id());
        produto.atualizarCadastro(dadosAtualizar);
        dadosAtualizar.categoria_id().ifPresent(id -> {
            Categoria categoria = categoriaService.findCategoriaById(id);
            produto.atualizarCadastro(categoria);
        });
        dadosAtualizar.unidade_id().ifPresent(id -> {
            Unidade unidade = unidadeService.findUnidadeById(id);
            produto.atualizarCadastro(unidade);
        });
        return new DadosDetalhamentoProduto(produto);
    }

    public void deletar(Long id) {
        Produto produto = this.findProdutoById(id);
        produto.desabilitarCadastro();
    }
}
