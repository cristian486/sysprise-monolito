package br.com.sysprise.model.funcionario;

import br.com.sysprise.model.contato.DadosCadastroContato;
import br.com.sysprise.model.endereco.DadosCadastroEndereco;
import br.com.sysprise.model.funcionario.usuario.DadosCadastroUsuario;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public record DadosCadastroFuncionario(@NotBlank(message = "Obrigatório o preenchimento do nome")
                                       String nome,
                                       @Min(value = 18, message = "A idade mínima para contratação é de dezoito anos")
                                       @NotNull(message = "Obrigatório o envio da idade")
                                       Integer idade,
                                       @Pattern(regexp = "\\d{11}", message = "O padrão esperado é de onze dígitos, sem ponto ou traço!")
                                       @NotBlank(message = "Obrigatório o preenchimento do cpf")
                                       String cpf,
                                       @Pattern(regexp = "\\d{11,12}", message = "O padrão esperado é de onze até doze dígitos, sem ponto ou traço!")
                                       @NotBlank(message = "Obrigatório o preenchimento do rg")
                                       String rg,
                                       String orgaoEmissor,
                                       @NotNull(message = "Obrigatório o envio do gênero")
                                       Genero genero,
                                       @Past(message = "A data de nascimento deve ser no passado")
                                       @NotNull(message = "Obrigatório o envio da data de nascimento")
                                       LocalDate dataDeNascimento,
                                       @NotNull(message = "Obrigatório o envio do estado civil")
                                       EstadoCivil estadoCivil,
                                       @NotBlank(message = "Obrigatório o preenchimento do cargo")
                                       String cargo,
                                       @Digits(integer = 5, fraction = 2, message = "O padrão esperado é de até cinco dígitos antes da vírgula e até três dígitos após a vírgula")
                                       @NotNull(message = "Obrigatório o envio da remuneração")
                                       BigDecimal remuneracao,
                                       @NotBlank(message = "Obrigatório o preenchimento da jornada de trabalho")
                                       String jornadaDeTrabalho,
                                       @Valid
                                       @NotNull(message = "Obrigatório o envio dos dados de cadastro de contato")
                                       List<DadosCadastroContato> contatos,
                                       @Valid
                                       @NotNull(message = "Obrigatório o envio dos dados de cadastro de endereço")
                                       DadosCadastroEndereco endereco,
                                       @Valid
                                       Optional<DadosCadastroUsuario> usuario) {
}
