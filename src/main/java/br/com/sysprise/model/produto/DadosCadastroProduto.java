package br.com.sysprise.model.produto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record DadosCadastroProduto(@NotBlank(message = "Obrigatório o preenchimento do nome")
                                   String nome,
                                   @NotBlank(message = "Obrigatório o preenchimento da descrição")
                                   String descricao,
                                   String observacao,
                                   @Pattern(regexp = "\\d{13,15}", message = "O padrão esperado é até quinze dígitos numéricos, sem ponto ou vírgula.")
                                   @NotBlank(message = "Obrigatório o preenchimento do código de barras")
                                   String codigoDeBarras,
                                   @Digits(integer = 5, fraction = 4, message = "O padrão esperado é até cinco dígitos antes da vírgula e quatro dígitos após a vírgula")
                                   Double pesoBruto,
                                   @Digits(integer = 5, fraction = 4, message = "O padrão esperado é até cinco dígitos antes da vírgula e quatro dígitos após a vírgula")
                                   Double pesoLiquido,
                                   @Digits(integer = 5, fraction = 3, message = "O padrão esperado é até cinco dígitos antes da vírgula e três dígitos após a vírgula")
                                   @NotNull(message = "Obrigatório o envio do preço de compra")
                                   BigDecimal precoDeCompra,
                                   @Digits(integer = 5, fraction = 3, message = "O padrão esperado é até cinco dígitos antes da vírgula e três dígitos após a vírgula")
                                   @NotNull(message = "Obrigatório o envio do preço de venda")
                                   BigDecimal precoDeVenda,
                                   @Digits(integer = 5, fraction = 3, message = "O padrão esperado é até cinco dígitos antes da vírgula e três dígitos após a vírgula")
                                   Double altura,
                                   @Digits(integer = 5, fraction = 3, message = "O padrão esperado é até cinco dígitos antes da vírgula e três dígitos após a vírgula")
                                   Double largura,
                                   @Digits(integer = 5, fraction = 3, message = "O padrão esperado é até cinco dígitos antes da vírgula e três dígitos após a vírgula")
                                   Double profundidade,
                                   @Digits(integer = 5, fraction = 3, message = "O padrão esperado é até cinco dígitos antes da vírgula e três dígitos após a vírgula")
                                   Double estoqueMinimo,
                                   @NotNull(message = "Obrigatório o envio do campo de venda fracionada")
                                   Boolean vendaFracionada,
                                   @Min(1)
                                   @NotNull(message = "Obrigatório o envio do ID da categoria")
                                   Long categoria_id,
                                   @Min(1)
                                   @NotNull(message = "Obrigatório o envio do ID da unidade")
                                   Long unidade_id) {
}
