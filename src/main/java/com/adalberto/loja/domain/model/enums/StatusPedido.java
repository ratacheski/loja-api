package com.adalberto.loja.domain.model.enums;

public enum StatusPedido {

	ANDAMENTO(1, "Em Andamento"), FINALIZADO(2, "Finalizado"), CANCELADO(3, "Cancelado"), ENTREGUE(4, "Entregue");

	private int valor;
	private String msg;

	StatusPedido(int valor, String msg) {
		this.valor = valor;
		this.msg = msg;

	}

	public int getValor() {
		return this.valor;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return msg;
	}

	public String getMsg() {
		return this.msg;
	}

}
