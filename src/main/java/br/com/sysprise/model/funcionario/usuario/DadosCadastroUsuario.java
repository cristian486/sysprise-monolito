package br.com.sysprise.model.funcionario.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUsuario(@NotBlank(message = "Obrigatório o preenchimento do login")
                                   String login,
                                   @NotBlank(message = "Obrigatório o preenchimento da senha")
                                   String senha) {
}
