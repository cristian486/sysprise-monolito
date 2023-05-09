package br.com.sysprise.model.contato;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

import java.util.Optional;


public record DadosAtualizarContato(@Min(1)
                                    Optional<Long> id,
                                    @Email
                                    String email,
                                    @Pattern(regexp = "\\d{8,13}", message = "O padrão esperado é de oito a treze dígitos sem ponto, parênteses ou traço!")
                                    String telefone,
                                    Boolean remover) {

    public DadosCadastroContato converterParaCadastroContato() {
        return new DadosCadastroContato(this.email, this.telefone);
    }

    public boolean deveRemover() {
        return this.remover != null && this.remover;
    }
}
