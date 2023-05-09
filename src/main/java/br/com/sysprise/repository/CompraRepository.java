package br.com.sysprise.repository;

import br.com.sysprise.model.compra.Compra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

    Page<Compra> findAllByHabilitadoTrue(Pageable pageable);
}
