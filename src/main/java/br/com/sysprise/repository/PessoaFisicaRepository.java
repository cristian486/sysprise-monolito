package br.com.sysprise.repository;

import br.com.sysprise.model.pessoa.fisica.PessoaFisica;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Long> {


    Page<PessoaFisica> findAllByHabilitadoTrue(Pageable pageable);
}
