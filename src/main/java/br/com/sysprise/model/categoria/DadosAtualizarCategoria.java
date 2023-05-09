package br.com.sysprise.model.categoria;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarCategoria(@Min(1)
                                      @NotNull(message = "Obrigatório o envio do ID da categoria")
                                      Long id,
                                      String nome,
                                      String descricao) {
}
