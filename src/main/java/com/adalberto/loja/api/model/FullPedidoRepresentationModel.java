package com.adalberto.loja.api.model;

import java.time.OffsetDateTime;
import java.util.Set;

import com.adalberto.loja.domain.model.ItemPedido;
import com.adalberto.loja.domain.model.enums.StatusPedido;

public class FullPedidoRepresentationModel {

	private Integer id;
	private OffsetDateTime data;
	private StatusPedido status;
	private ClienteRepresentationModel cliente;
	private Set<ItemPedido> itens;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OffsetDateTime getData() {
		return data;
	}

	public void setData(OffsetDateTime data) {
		this.data = data;
	}

	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}

	public ClienteRepresentationModel getCliente() {
		return cliente;
	}

	public void setCliente(ClienteRepresentationModel cliente) {
		this.cliente = cliente;
	}

	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}
	public double getValorTotal(){
		
		double valorTotal = 0;
		for(ItemPedido item : itens) {
			valorTotal += item.getValorTotal();
		}
		return valorTotal;
	}

}
