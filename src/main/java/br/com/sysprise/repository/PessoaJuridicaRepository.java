package br.com.sysprise.repository;

import br.com.sysprise.model.pessoa.juridica.PessoaJuridica;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Long> {

    Page<PessoaJuridica> findAllByHabilitadoTrue(Pageable pageable);
}
