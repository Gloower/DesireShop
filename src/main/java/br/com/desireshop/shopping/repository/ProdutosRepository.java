package br.com.desireshop.shopping.repository;

import br.com.desireshop.shopping.model.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos, Long> {

    public List<Produtos> findAllByNomeContainingIgnoreCase(@Param("nome")String nome);
}
