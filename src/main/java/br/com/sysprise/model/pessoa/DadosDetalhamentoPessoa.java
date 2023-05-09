package br.com.sysprise.model.pessoa;

import br.com.sysprise.model.contato.DadosListagemContato;
import br.com.sysprise.model.endereco.DadosDetalhamentoEndereco;
import br.com.sysprise.model.tipo.DadosDetalhamentoTipo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DadosDetalhamentoPessoa {

    protected DadosDetalhamentoEndereco endereco;
    protected List<DadosDetalhamentoTipo> tipos;
    protected List<DadosListagemContato> contatos;
}
