package br.com.sysprise.model.pessoa.juridica;

import br.com.sysprise.model.contato.DadosAtualizarContato;
import br.com.sysprise.model.endereco.DadosAtualizarEndereco;
import br.com.sysprise.model.pessoa.DadosAtualizarPessoa;
import br.com.sysprise.model.pessoa.DadosAtualizarTipoPessoa;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
public final class DadosAtualizarPessoaJuridica extends DadosAtualizarPessoa {
    @Min(1)
    @NotNull(message = "Obrigatório o envio do ID da pessoa jurídica")
    private Long id;
    private String razaoSocial;
    private String nomeFantasia;
    @Pattern(regexp = "\\d{14}", message = "O padrão esperado é quatorze dígitos sem ponto, barra ou traço!")
    private String cnpj;
}
