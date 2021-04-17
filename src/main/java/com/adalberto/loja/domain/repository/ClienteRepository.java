package com.adalberto.loja.domain.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.adalberto.loja.domain.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	List<Cliente> findAllByNomeContaining(String nome);

	Optional<Cliente> findByCpf(String cpf);

}
