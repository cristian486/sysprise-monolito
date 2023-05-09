package br.com.sysprise.service;

import br.com.sysprise.model.funcionario.Funcionario;
import br.com.sysprise.model.funcionario.usuario.DadosCadastroUsuario;
import br.com.sysprise.model.funcionario.usuario.DadosListagemUsuario;
import br.com.sysprise.model.funcionario.usuario.Usuario;
import br.com.sysprise.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;

    public void cadastrar(DadosCadastroUsuario dadosCadastro, Funcionario funcionario) {
        Usuario usuario = new Usuario(dadosCadastro.login(), passwordEncoder.encode(dadosCadastro.senha()), funcionario);
    }

    public void atualizar(Usuario usuario, DadosCadastroUsuario dadosCadastro) {
        usuario.atualizarCadastro(dadosCadastro.login(), passwordEncoder.encode(dadosCadastro.senha()));
    }

    public Page<DadosListagemUsuario> listar(Pageable pageable) {
        return usuarioRepository.findAll(pageable).map(DadosListagemUsuario::new);
    }

    public DadosListagemUsuario buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário requisitado não foi encontrado!"));
        return new DadosListagemUsuario(usuario);
    }
}
