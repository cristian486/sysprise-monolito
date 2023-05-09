package br.com.sysprise.model.categoria;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroCategoria(@NotBlank(message = "Obrigatório o preenchimento do nome")
                                     String nome,
                                     String descricao) {
}
