package br.com.sysprise.model.funcionario.usuario;

import br.com.sysprise.model.funcionario.Funcionario;
import br.com.sysprise.model.funcionario.usuario.role.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String senha;
    @OneToOne(mappedBy = "usuario")
    private Funcionario funcionario;
    @OneToOne
    private Role role;

    public Usuario(String login, String senha, Funcionario funcionario) {
        this.login = login;
        this.senha = senha;
        this.funcionario = funcionario;
    }

    public void atualizarCadastro(String login, String senha) {
        if(login != null && !login.isEmpty())
            this.login = login;

        if(senha != null && !senha.isEmpty())
            this.senha = senha;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(this.role);
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.funcionario.getHabilitado();
    }
}
