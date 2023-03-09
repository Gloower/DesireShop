package br.com.desireshop.shopping.repository;

import br.com.desireshop.shopping.model.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientesRepository extends JpaRepository <Clientes, Long> {
    public List <Clientes> findAllByNomeContainingIgnoreCase(String nome);
    public Optional<Clientes> findByEmail(String email);
}
