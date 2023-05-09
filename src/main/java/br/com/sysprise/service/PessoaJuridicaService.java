package br.com.sysprise.service;

import br.com.sysprise.model.cidade.Cidade;
import br.com.sysprise.model.pessoa.juridica.*;
import br.com.sysprise.model.tipo.Tipo;
import br.com.sysprise.repository.PessoaJuridicaRepository;
import br.com.sysprise.service.pessoa.AtualizarPessoa;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PessoaJuridicaService {

    private final TipoService tipoService;
    private final CidadeService cidadeService;
    private List<AtualizarPessoa> atualizarAtributosPessoa;
    private final PessoaJuridicaRepository pessoaJuridicaRepository;

    public PessoaJuridica findPessoaJuridicaById(Long id) {
        return pessoaJuridicaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("A pessoa jurídica requisitada não foi encontrada!"));
    }

    public Page<DadosListagemPessoaJuridica> listar(Pageable pageable) {
        return pessoaJuridicaRepository.findAllByHabilitadoTrue(pageable).map(DadosListagemPessoaJuridica::new);
    }

    public DadosDetalhamentoPessoaJuridica detalhar(Long id) {
        PessoaJuridica pessoaJuridica = this.findPessoaJuridicaById(id);
        return new DadosDetalhamentoPessoaJuridica(pessoaJuridica);
    }

    public DadosDetalhamentoPessoaJuridica cadastrar(DadosCadastroPessoaJuridica dadosCadastro){
        List<Tipo> tipos = tipoService.findAllById(dadosCadastro.tipos());
        Cidade cidade = cidadeService.findCidadeById(dadosCadastro.endereco().cidade_id());
        PessoaJuridica pessoaJuridica = new PessoaJuridica(dadosCadastro, tipos, cidade);
        pessoaJuridicaRepository.save(pessoaJuridica);
        return new DadosDetalhamentoPessoaJuridica(pessoaJuridica);
    }

    public DadosDetalhamentoPessoaJuridica atualizar(DadosAtualizarPessoaJuridica dadosAtualizar) {
        PessoaJuridica pessoaJuridica = this.findPessoaJuridicaById(dadosAtualizar.getId());
        pessoaJuridica.atualizarCadastro(dadosAtualizar);
        atualizarAtributosPessoa.forEach(a -> a.executar(dadosAtualizar, pessoaJuridica));
        pessoaJuridicaRepository.flush();
        return new DadosDetalhamentoPessoaJuridica(pessoaJuridica);
    }

    public void deletar(Long id) {
        PessoaJuridica pessoaJuridica = this.findPessoaJuridicaById(id);
        // TODO
    }
}
