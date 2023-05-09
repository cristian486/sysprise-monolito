package br.com.sysprise.model.unidade;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarUnidade(@Min(1)
                                    @NotNull(message = "Obrigatório o envio do ID da unidade")
                                    Long id,
                                    String nome,
                                    String abreviacao) {
}
