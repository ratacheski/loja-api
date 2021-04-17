package com.adalberto.loja.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adalberto.loja.domain.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

		List<Produto> findAllByNomeContaining(String nome);

		
}
