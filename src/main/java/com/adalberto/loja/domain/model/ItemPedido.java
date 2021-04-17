package com.adalberto.loja.domain.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import com.adalberto.loja.domain.model.keys.ItemPedidoPk;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemPedido {

	@EmbeddedId
	private ItemPedidoPk id;

	@Column(name = "quantidade")
	private int quantidade;

	@Column(name = "valor_unitario")
	private double valorUnitario;

	@ManyToOne
	@MapsId("pedidoId")
	@JoinColumn(name = "pedido_id")
	@JsonIgnore
	private Pedido pedido;

	@ManyToOne
	@MapsId("produtoId")
	@JoinColumn(name = "produto_id")
	private Produto produto;

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
		ItemPedido other = (ItemPedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public ItemPedidoPk getId() {
		return id;
	}

	public void setId(ItemPedidoPk id) {
		this.id = id;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public double getValorTotal() {
		return this.valorUnitario * this.quantidade;
	}

}
