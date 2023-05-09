package br.com.sysprise.service.pessoa;

import br.com.sysprise.model.pessoa.DadosAtualizarPessoa;
import br.com.sysprise.model.pessoa.DadosAtualizarTipoPessoa;
import br.com.sysprise.model.pessoa.Pessoa;
import br.com.sysprise.model.tipo.Tipo;
import br.com.sysprise.service.TipoService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component("atualizarTipo")
public class AtualizarPessoaTipo extends AtualizarPessoa {

    private final TipoService tipoService;
    public AtualizarPessoaTipo(TipoService tipoService) {
        super(Optional.empty());
        this.tipoService = tipoService;
    }

    @Override
    public void atualizar(DadosAtualizarPessoa dadosAtualizar, Pessoa pessoa) {
        List<DadosAtualizarTipoPessoa> dadosAtualizarTipo = dadosAtualizar.getTipos().get();

        dadosAtualizarTipo.forEach(tipoAtualizar -> {
            pessoa.buscarTipoPorId(tipoAtualizar.id())
                    .ifPresentOrElse(tipoCadastrado -> {
                        if(tipoAtualizar.deveRemover())
                            pessoa.removerTipo(tipoCadastrado);
                    }, () -> {
                        Tipo tipo = tipoService.findTipoById(tipoAtualizar.id());
                        pessoa.adicionarTipo(tipo);
                    });
        });
    }

    @Override
    public boolean haRegistroParaAtualizar(DadosAtualizarPessoa dadosAtualizar) {
        return dadosAtualizar.getTipos() != null && dadosAtualizar.getTipos().isPresent();
    }
}
