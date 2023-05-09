package br.com.sysprise.model.endereco;

import br.com.sysprise.model.cidade.Cidade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicReference;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"rua", "numero", "complemento"})
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rua;
    private Integer numero;
    private String bairro;
    private String complemento;
    private String cep;
    @OneToOne(fetch = FetchType.LAZY)
    private Cidade cidade;

    public Endereco(DadosCadastroEndereco endereco, Cidade cidade) {
        this.rua = endereco.rua();
        this.numero = endereco.numero();
        this.bairro = endereco.bairro();
        this.complemento = endereco.complemento();
        this.cep = endereco.cep();
        this.cidade = cidade;
    }

    public void atualizarCadastro(DadosAtualizarEndereco endereco, Cidade cidade) {
        if(endereco.rua() != null && !endereco.rua().isEmpty())
            this.rua = endereco.rua();

        if(endereco.numero() != null && endereco.numero().intValue() > 0)
            this.numero = endereco.numero();

        if(endereco.bairro() != null && !endereco.bairro().isEmpty())
            this.bairro = endereco.bairro();

        if(endereco.complemento() != null && !endereco.complemento().isEmpty())
            this.complemento = endereco.complemento();

        if(endereco.cep() != null && !endereco.cep().isEmpty())
            this.cep = endereco.cep();

        if(cidade!= null && !cidade.equals(this.cidade))
            this.cidade = cidade;
    }
}
