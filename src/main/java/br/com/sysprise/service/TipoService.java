package br.com.sysprise.service;

import br.com.sysprise.infra.exception.ValidacaoException;
import br.com.sysprise.model.tipo.*;
import br.com.sysprise.repository.TipoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TipoService {

    private final TipoRepository tipoRepository;

    public List<Tipo> findAllById(List<Long> ids) {
        return tipoRepository.findAllById(ids);
    }

    public Tipo findTipoById(Long id) {
        return tipoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("O tipo requisitado não foi encontrado!"));
    }

    public Page<DadosListagemTipo> listar(Pageable pageable) {
        return tipoRepository.findAll(pageable).map(DadosListagemTipo::new);
    }

    public DadosDetalhamentoTipo detalhar(Long id) {
        Tipo tipo = this.findTipoById(id);
        return new DadosDetalhamentoTipo(tipo);
    }

    public DadosDetalhamentoTipo cadastrar(DadosCadastroTipo dadosCadastro) {
        Tipo tipo = new Tipo(dadosCadastro);
        tipoRepository.save(tipo);
        return new DadosDetalhamentoTipo(tipo);
    }

    public DadosDetalhamentoTipo atualizar(DadosAtualizarTipo dadosAtualizar) {
        Tipo tipo = this.findTipoById(dadosAtualizar.id());
        tipo.atualizarCadastro(dadosAtualizar);
        return new DadosDetalhamentoTipo(tipo);
    }

    public void deletar(Long id) {
        Tipo tipo = this.findTipoById(id);
        Boolean haPessoasVinculadas = tipoRepository.haPessoasVinculadasTipo(tipo.getId()).longValue() == 0l;
        if(haPessoasVinculadas) throw new ValidacaoException("Tipo não pode ser deletado pois há pessoas vinculadas a ele");
        tipoRepository.delete(tipo);
    }
}
