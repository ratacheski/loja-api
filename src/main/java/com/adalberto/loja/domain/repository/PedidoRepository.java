package com.adalberto.loja.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adalberto.loja.domain.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
	

}
