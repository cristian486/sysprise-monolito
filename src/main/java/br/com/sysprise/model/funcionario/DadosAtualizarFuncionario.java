package br.com.sysprise.model.funcionario;

import br.com.sysprise.model.contato.DadosAtualizarContato;
import br.com.sysprise.model.endereco.DadosAtualizarEndereco;
import br.com.sysprise.model.funcionario.usuario.DadosCadastroUsuario;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public record DadosAtualizarFuncionario(@Min(1)
                                        @NotNull(message = "Obrigatório o envio do ID do funcionário")
                                        Long id,
                                        String nome,
                                        EstadoCivil estadoCivil,
                                        String cargo,
                                        BigDecimal remuneracao,
                                        String jornadaDeTrabalho,
                                        @Valid
                                        Optional<DadosAtualizarEndereco> endereco,
                                        @Valid
                                        Optional<List<DadosAtualizarContato>> contatos,
                                        @Valid
                                        Optional<DadosCadastroUsuario> usuario) {
}
