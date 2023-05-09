package br.com.sysprise.model.venda.itemvenda;

import br.com.sysprise.infra.exception.ValidacaoException;
import br.com.sysprise.model.produto.Produto;
import br.com.sysprise.model.venda.Venda;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"venda", "produto"})
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Venda venda;
    @ManyToOne
    private Produto produto;
    private BigDecimal precoUnitario;
    private Double quantidade;

    public ItemVenda(Double quantidade, Produto produto, Venda venda) {
        this.venda = venda;
        this.produto = produto;
        this.precoUnitario = produto.getPrecoDeVenda();
        this.quantidade = quantidade;
        this.verificarSePodeVenderQuantidadeFracionada();
    }

    private void verificarSePodeVenderQuantidadeFracionada() {
        if(!produto.permitidaVendaFracionada() && this.quantidade % 1 != 0)
            throw new ValidacaoException("Não é possível a venda fracionada do produto " + produto.getNome());
    }

    public BigDecimal valorTotal() {
        return precoUnitario.multiply(new BigDecimal(this.quantidade));
    }

    public void atualizarCadastro(DadosAtualizarItemVenda item) {
        if(item.quantidade() != null && item.quantidade().doubleValue() > 0.000)
            this.quantidade = item.quantidade();
    }
}
