package br.com.sysprise.model.tipo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarTipo(@Min(1)
                                 @NotNull(message = "Obrigatório o envio do ID do tipo")
                                 Long id,
                                 @NotBlank(message = "Obrigatório o preenchimento do nome")
                                 String nome) {
}
