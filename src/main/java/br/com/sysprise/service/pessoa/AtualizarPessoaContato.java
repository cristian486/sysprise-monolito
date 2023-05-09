package br.com.sysprise.service.pessoa;

import br.com.sysprise.model.contato.Contato;
import br.com.sysprise.model.contato.DadosAtualizarContato;
import br.com.sysprise.model.contato.DadosCadastroContato;
import br.com.sysprise.model.pessoa.DadosAtualizarPessoa;
import br.com.sysprise.model.pessoa.Pessoa;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component("atualizarContato")
public class AtualizarPessoaContato extends AtualizarPessoa {

    public AtualizarPessoaContato(@Qualifier("atualizarEndereco") Optional<AtualizarPessoa> proximo) {
        super(proximo);
    }

    @Override
    public void atualizar(DadosAtualizarPessoa dadosAtualizar, Pessoa pessoa) {
        List<DadosAtualizarContato> contatosAtualizar = dadosAtualizar.getContatos().get();
        contatosAtualizar.forEach(contatoAtualizar -> {
            contatoAtualizar.id().ifPresentOrElse(id -> {
                pessoa.buscarContatoPorId(id)
                      .ifPresent(contatoCadastrado -> {
                          if(contatoAtualizar.deveRemover())
                            pessoa.removerContato(contatoCadastrado);
                          else
                            contatoCadastrado.atualizarCadastro(contatoAtualizar);
                      });
            }, () -> {
                DadosCadastroContato dadosCadastro = contatoAtualizar.converterParaCadastroContato();
                pessoa.adicionarContato(new Contato(dadosCadastro));
            });

        });
    }

    @Override
    public boolean haRegistroParaAtualizar(DadosAtualizarPessoa dadosAtualizar) {
        return dadosAtualizar.getContatos() != null && dadosAtualizar.getContatos().isPresent();
    }

}
