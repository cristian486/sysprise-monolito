package br.com.sysprise.model.pessoa.fisica;

import br.com.sysprise.model.pessoa.DadosAtualizarPessoa;
import br.com.sysprise.model.pessoa.Genero;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public final class DadosAtualizarPessoaFisica extends DadosAtualizarPessoa {
    @Min(1)
    @NotNull(message = "Obrigatório o envio do ID da pessoa física")
    private Long id;
    private String nome;
    @Pattern(regexp = "\\d{11}", message = "O padrão esperado é de onze dígitos sem ponto ou traço.")
    private String cpf;
    private Genero genero;
    @Past(message = "A data de nascimento não pode ser presente ou futura")
    private LocalDate dataDeNascimento;

}
