package br.com.sysprise.service.pessoa;

import br.com.sysprise.model.cidade.Cidade;
import br.com.sysprise.model.endereco.DadosAtualizarEndereco;
import br.com.sysprise.model.pessoa.DadosAtualizarPessoa;
import br.com.sysprise.model.pessoa.Pessoa;
import br.com.sysprise.service.CidadeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("atualizarEndereco")
public class AtualizarPessoaEndereco extends AtualizarPessoa {

    private final CidadeService cidadeService;

    public AtualizarPessoaEndereco(@Qualifier("atualizarTipo") Optional<AtualizarPessoa> proximo, CidadeService cidadeService) {
        super(proximo);
        this.cidadeService = cidadeService;
    }

    @Override
    public void atualizar(DadosAtualizarPessoa dadosAtualizar, Pessoa pessoa) {
        Cidade cidade = null;
        DadosAtualizarEndereco dadosAtualizarEndereco = dadosAtualizar.getEndereco().get();

        if(dadosAtualizarEndereco.cidade_id().isPresent())
            cidade = cidadeService.findCidadeById(dadosAtualizarEndereco.cidade_id().get());

        pessoa.atualizarEndereco(dadosAtualizarEndereco, cidade);
    }

    @Override
    public boolean haRegistroParaAtualizar(DadosAtualizarPessoa dadosAtualizar) {
        return dadosAtualizar.getEndereco() != null && dadosAtualizar.getEndereco().isPresent();
    }

}
