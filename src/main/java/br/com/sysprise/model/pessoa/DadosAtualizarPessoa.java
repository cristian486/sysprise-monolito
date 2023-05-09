package br.com.sysprise.model.pessoa;

import br.com.sysprise.model.contato.DadosAtualizarContato;
import br.com.sysprise.model.endereco.DadosAtualizarEndereco;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DadosAtualizarPessoa {
    @Valid
    private Optional<DadosAtualizarEndereco> endereco;
    @Valid
    private Optional<List<DadosAtualizarTipoPessoa>> tipos;
    @Valid
    private Optional<List<DadosAtualizarContato>> contatos;
}
