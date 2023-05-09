package br.com.sysprise.model.contato;

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
@EqualsAndHashCode(of = "email")
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String telefone;

    public Contato(DadosCadastroContato contato) {
        this.email = contato.email();
        this.telefone = contato.telefone();
    }

    public void atualizarCadastro(DadosAtualizarContato dadosAtualizar) {
        if(dadosAtualizar.telefone() != null && !dadosAtualizar.telefone().isEmpty())
            this.telefone = dadosAtualizar.telefone();

        if(dadosAtualizar.email() != null && !dadosAtualizar.email().isEmpty())
            this.email = dadosAtualizar.email();
    }
}
