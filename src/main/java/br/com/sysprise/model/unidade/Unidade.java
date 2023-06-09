package br.com.sysprise.model.unidade;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"nome"})
public class Unidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String abreviacao;

    public Unidade(DadosCadastroUnidade dadosCadastro) {
        this.nome = dadosCadastro.nome();
        this.abreviacao = dadosCadastro.abreviacao();
    }

    public void atualizarCadastro(DadosAtualizarUnidade dadosAtualizar) {
        if(dadosAtualizar.nome() != null && !dadosAtualizar.nome().isEmpty())
            this.nome = dadosAtualizar.nome();

        if(dadosAtualizar.abreviacao() != null && !dadosAtualizar.abreviacao().isEmpty())
            this.abreviacao = dadosAtualizar.abreviacao();
    }
}
