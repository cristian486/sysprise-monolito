package br.com.sysprise.service;

import br.com.sysprise.model.cidade.Cidade;
import br.com.sysprise.model.pessoa.DadosAtualizarTipoPessoa;
import br.com.sysprise.model.pessoa.fisica.*;
import br.com.sysprise.model.tipo.Tipo;
import br.com.sysprise.repository.PessoaFisicaRepository;
import br.com.sysprise.service.pessoa.AtualizarPessoa;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@AllArgsConstructor
public class PessoaFisicaService {

    private final TipoService tipoService;
    private final CidadeService cidadeService;
    private List<AtualizarPessoa> atualizarAtributosPessoa;
    private final PessoaFisicaRepository pessoaFisicaRepository;

    public PessoaFisica findPessoaFisicaById(Long id) {
        return pessoaFisicaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("A pessoa física requisitada não foi encontrada!"));
    }

    public Page<DadosListagemPessoaFisica> listar(Pageable pageable) {
        return pessoaFisicaRepository.findAllByHabilitadoTrue(pageable).map(DadosListagemPessoaFisica::new);
    }

    public DadosDetalhamentoPessoaFisica detalhar(Long id) {
        PessoaFisica pessoaFisica = this.findPessoaFisicaById(id);
        return new DadosDetalhamentoPessoaFisica(pessoaFisica);
    }

    public DadosDetalhamentoPessoaFisica cadastrar(DadosCadastroPessoaFisica dadosCadastro) {
        List<Tipo> tipos = tipoService.findAllById(dadosCadastro.tipos());
        Cidade cidade = cidadeService.findCidadeById(dadosCadastro.endereco().cidade_id());
        PessoaFisica pessoaFisica = new PessoaFisica(dadosCadastro, tipos, cidade);
        pessoaFisicaRepository.save(pessoaFisica);
        return new DadosDetalhamentoPessoaFisica(pessoaFisica);
    }

    public DadosDetalhamentoPessoaFisica atualizar(DadosAtualizarPessoaFisica dadosAtualizar) {
        PessoaFisica pessoaFisica = this.findPessoaFisicaById(dadosAtualizar.getId());
        pessoaFisica.atualizarCadastro(dadosAtualizar);
        atualizarAtributosPessoa.forEach(a -> a.executar(dadosAtualizar, pessoaFisica));
        pessoaFisicaRepository.flush();
        return new DadosDetalhamentoPessoaFisica(pessoaFisica);
    }

    public void deletar(Long id) {
        PessoaFisica pessoaFisica = this.findPessoaFisicaById(id);
        // TODO
    }
}
