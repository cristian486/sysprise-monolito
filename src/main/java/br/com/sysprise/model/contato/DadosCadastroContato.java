package br.com.sysprise.model.contato;

import br.com.sysprise.infra.validation.OneCannotBeNullAndEmpty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

@OneCannotBeNullAndEmpty(campos = {"email", "telefone"}, message = "Obrigatório o preenchimento do e-mail ou do telefone")
public record DadosCadastroContato(@Email
                                   String email,
                                   @Pattern(regexp = "\\d{8,13}", message = "O padrão esperado é de oito a treze dígitos sem ponto, parênteses ou traço!")
                                   String telefone) {
}
