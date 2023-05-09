package br.com.sysprise.model.compra.itemcompra;

import br.com.sysprise.model.compra.Compra;
import br.com.sysprise.model.produto.Produto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"compra", "produto"})
public class ItemCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Compra compra;
    @ManyToOne
    private Produto produto;
    private Double quantidade;

    public void atualizarCadastro(DadosAtualizarItemCompra item) {
        if(item.quantidade() != null && item.quantidade().doubleValue() > 0.000d)
            this.quantidade = item.quantidade();
    }
}
