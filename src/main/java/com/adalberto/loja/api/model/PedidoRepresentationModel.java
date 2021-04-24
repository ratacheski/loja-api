package com.adalberto.loja.api.model;

import java.time.OffsetDateTime;

import com.adalberto.loja.domain.model.enums.FormaPagamento;
import com.adalberto.loja.domain.model.enums.StatusPedido;

public class PedidoRepresentationModel {

	private Integer id;
	private OffsetDateTime data;
	private StatusPedido status;
	private ClienteRepresentationModel cliente;
	private FormaPagamento formaPagamento;

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

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

}
