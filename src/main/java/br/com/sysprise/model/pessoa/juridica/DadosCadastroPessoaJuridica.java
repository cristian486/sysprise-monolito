package br.com.sysprise.model.pessoa.juridica;

import br.com.sysprise.model.contato.DadosCadastroContato;
import br.com.sysprise.model.endereco.DadosCadastroEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record DadosCadastroPessoaJuridica(@NotBlank(message = "Obrigatório o preenchimento da razão social")
                                          String razaoSocial,
                                          String nomeFantasia,
                                          @Pattern(regexp = "\\d{14}", message = "O padrão esperado é quatorze dígitos sem ponto, barra ou traço!")
                                          @NotBlank(message = "Obrigatório o preenchimento do cnpj")
                                          String cnpj,
                                          @Valid
                                          @NotNull(message = "Obrigatório o envio dos dados para cadastro do endereço")
                                          DadosCadastroEndereco endereco,
                                          @NotNull(message = "Obrigatório o envio dos tipos da pessoa")
                                          @NotEmpty(message = "Obrigatório ao menos um tipo para a pessoa")
                                          List<Long> tipos,
                                          @Valid
                                          @NotEmpty(message = "Obrigatório ao menos um contato para a pessoa")
                                          @NotNull(message = "Obrigatório o envio dos dados para cadastro do contato")
                                          List<DadosCadastroContato> contatos) {
}
