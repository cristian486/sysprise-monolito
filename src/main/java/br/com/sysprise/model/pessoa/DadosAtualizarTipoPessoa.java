package br.com.sysprise.model.pessoa;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarTipoPessoa(@Min(1)
                                       @NotNull(message = "Obrigatório o envio do ID do tipo")
                                       Long id,
                                       Boolean remover) {
    public boolean deveRemover() {
        return this.remover != null && this.remover;
    }
}
