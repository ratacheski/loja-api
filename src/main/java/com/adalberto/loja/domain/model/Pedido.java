package com.adalberto.loja.domain.model;

import java.util.Date;

import com.adalberto.loja.domain.model.enums.StatusPedido;

public class Pedido {

	private int id;
	private Cliente cliente;
	private Date data;
	private StatusPedido status;

	public Pedido() {
		super();

	}

	public Pedido(int id, Cliente cliente, Date data, StatusPedido status) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.data = data;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}

}
