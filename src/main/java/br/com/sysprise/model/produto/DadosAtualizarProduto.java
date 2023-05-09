package br.com.sysprise.model.produto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.Optional;

public record DadosAtualizarProduto(@Min(1)
                                    @NotNull(message = "Obrigatório o envio do ID do produto")
                                    Long id,
                                    String nome,
                                    String descricao,
                                    String observacao,
                                    @Pattern(regexp = "\\d{13,15}", message = "O padrão esperado é até quinze dígitos numéricos, sem ponto ou vírgula.")
                                    String codigoDeBarras,
                                    @Digits(integer = 5, fraction = 4, message = "O padrão esperado é até cinco dígitos antes da vírgula e quatro dígitos após a vírgula")
                                    Double pesoBruto,
                                    @Digits(integer = 5, fraction = 4, message = "O padrão esperado é até cinco dígitos antes da vírgula e quatro dígitos após a vírgula")
                                    Double pesoLiquido,
                                    @Digits(integer = 8, fraction = 3, message = "O padrão esperado é até oito dígitos antes da vírgula e três dígitos após a vírgula")
                                    BigDecimal precoDeCompra,
                                    @Digits(integer = 8, fraction = 3, message = "O padrão esperado é até oito dígitos antes da vírgula e três dígitos após a vírgula")
                                    BigDecimal precoDeVenda,
                                    @Digits(integer = 5, fraction = 3, message = "O padrão esperado é até cinco dígitos antes da vírgula e três dígitos após a vírgula")
                                    Double altura,
                                    @Digits(integer = 5, fraction = 3, message = "O padrão esperado é até cinco dígitos antes da vírgula e três dígitos após a vírgula")
                                    Double largura,
                                    @Digits(integer = 5, fraction = 3, message = "O padrão esperado é até cinco dígitos antes da vírgula e três dígitos após a vírgula")
                                    Double profundidade,
                                    @Digits(integer = 5, fraction = 3, message = "O padrão esperado é até cinco dígitos antes da vírgula e três dígitos após a vírgula")
                                    Double estoqueMinimo,
                                    Boolean vendaFracionada,
                                    @Min(1)
                                    Optional<Long> categoria_id,
                                    @Min(1)
                                    Optional<Long> unidade_id) {
}
