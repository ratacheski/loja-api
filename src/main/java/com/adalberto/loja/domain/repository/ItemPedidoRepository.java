package com.adalberto.loja.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adalberto.loja.domain.model.ItemPedido;
import com.adalberto.loja.domain.model.keys.ItemPedidoPk;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, ItemPedidoPk> {

}
