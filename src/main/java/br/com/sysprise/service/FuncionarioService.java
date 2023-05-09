package br.com.sysprise.service;

import br.com.sysprise.model.cidade.Cidade;
import br.com.sysprise.model.funcionario.*;
import br.com.sysprise.repository.FuncionarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicReference;

@Service
@AllArgsConstructor
public class FuncionarioService {

    private final CidadeService cidadeService;
    private final UsuarioService usuarioService;
    private final FuncionarioRepository funcionarioRepository;

    public Funcionario findFuncionarioById(Long id) {
        return funcionarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("O funcionário requisitado não foi encontrado!"));
    }

    public Page<DadosListagemFuncionario> listar(Pageable pageable) {
        return  funcionarioRepository.findAllByHabilitadoTrue(pageable).map(DadosListagemFuncionario::new);
    }

    public DadosDetalhamentoFuncionario detalhar(Long id) {
        Funcionario funcionario = this.findFuncionarioById(id);
        return new DadosDetalhamentoFuncionario(funcionario);
    }

    public DadosDetalhamentoFuncionario cadastrar(DadosCadastroFuncionario dadosCadastro) {
        Cidade cidade = cidadeService.findCidadeById(dadosCadastro.endereco().cidade_id());
        Funcionario funcionario = new Funcionario(dadosCadastro, cidade);
        dadosCadastro.usuario().ifPresent(dados -> usuarioService.cadastrar(dados, funcionario));
        funcionarioRepository.save(funcionario);
        return new DadosDetalhamentoFuncionario(funcionario);
    }

    public DadosDetalhamentoFuncionario atualizar(DadosAtualizarFuncionario dadosAtualizar) {
        Funcionario funcionario = this.findFuncionarioById(dadosAtualizar.id());
        funcionario.atualizarCadastro(dadosAtualizar);
        dadosAtualizar.endereco().ifPresent(dadosAtualizarEndereco -> {
            AtomicReference<Cidade> cidade = new AtomicReference<>();
            dadosAtualizarEndereco.cidade_id().ifPresent(id -> {
                cidade.set(cidadeService.findCidadeById(id));
            });

            funcionario.atualizarCadastro(dadosAtualizarEndereco, cidade.get());
        });
        dadosAtualizar.usuario().ifPresent(dados -> {
            if(funcionario.getUsuario() == null)
                usuarioService.cadastrar(dados, funcionario);
            else
                usuarioService.atualizar(funcionario.getUsuario(), dados);
        });
        funcionarioRepository.flush();
        return new DadosDetalhamentoFuncionario(funcionario);
    }

    public void deletar(Long id) {
        Funcionario funcionario = this.findFuncionarioById(id);
        funcionario.desabilitarCadastro();
    }
}
