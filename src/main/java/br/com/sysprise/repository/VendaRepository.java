package br.com.sysprise.repository;

import br.com.sysprise.model.venda.Venda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {


    Page<Venda> findAllByHabilitadoTrue(Pageable pageable);
}
