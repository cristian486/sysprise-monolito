package br.com.sysprise.repository;

import br.com.sysprise.model.categoria.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query("select case when count(p.id) > 0 then true else false end " +
            "from Produto p join p.categoria where p.categoria.id = :id")
    Boolean haProdutosVinculadosCategoria(Long id);
}
