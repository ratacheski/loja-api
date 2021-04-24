package com.adalberto.loja.domain.model;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Transient;

import com.adalberto.loja.domain.model.enums.FormaPagamento;
import com.adalberto.loja.domain.model.enums.StatusPedido;

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	private Cliente cliente;

	private OffsetDateTime data;

	@Transient
	private StatusPedido statusPedido;

	@Column(name = "status")
	private Integer valorStatusPedido;

	@Transient
	private FormaPagamento formaPagamento;

	@Column(name = "forma_pagamento")
	private Integer valorFormaPagamento;

	@OneToMany(mappedBy = "pedido")
	Set<ItemPedido> itens = new HashSet<ItemPedido>();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public OffsetDateTime getData() {
		return data;
	}

	public void setData(OffsetDateTime data) {
		this.data = data;
	}

	public StatusPedido getStatus() {
		return statusPedido;
	}

	public void setStatus(StatusPedido statusPedido) {
		this.statusPedido = statusPedido;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Integer getValorFormaPagamento() {
		return valorFormaPagamento;
	}

	public void setValorFormaPagamento(Integer valorFormaPagamento) {
		this.valorFormaPagamento = valorFormaPagamento;
	}

	public Integer getValorStatusPedido() {
		return valorStatusPedido;
	}

	public void setValorStatusPedido(Integer valorStatusPedido) {
		this.valorStatusPedido = valorStatusPedido;
	}

	@PostLoad
	void fillTransientFp() {
		if (valorFormaPagamento != null) {
			FormaPagamento forma = FormaPagamento.forma(valorFormaPagamento);
			formaPagamento = forma;
		}
		if (valorStatusPedido != null) {
			StatusPedido status = StatusPedido.status(valorStatusPedido);
			statusPedido = status;
		}
	}

	@PrePersist
	void fillPersistentFp() {
		if (formaPagamento != null) {
			valorFormaPagamento = formaPagamento.getValor();
		}
		if (statusPedido != null) {
			valorStatusPedido = statusPedido.getValor();
		}
	}

	public double getValorTotal() {

		double valorTotal = 0;
		for (ItemPedido item : itens) {
			valorTotal += item.getValorTotal();
		}
		return valorTotal;
	}
}
