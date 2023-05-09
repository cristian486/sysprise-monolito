package br.com.sysprise.repository;

import br.com.sysprise.model.cidade.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

    @Query("select case when count(e.id) > 0 then true else false end " +
            "from Endereco e join e.cidade where e.cidade.id = :id")
    Boolean haEnderecosVinculadosCidade(Long id);
}
