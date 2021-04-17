package com.adalberto.loja.domain.model.enums;

public enum FormaPagamento {

	DINHEIRO(1, "Dinheiro"), DEBITO(2, "Débito"), CREDITO(3, "Crédito"), PIX(4, "Pix");

	private int valor;
	private String msg;

	FormaPagamento(int valor, String msg) {
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
	
	public static FormaPagamento getByValor(int valor) {
		
		for(FormaPagamento forma : values()) {
			if(forma.valor == valor) {
				return forma;
			}
		}
		return null;
	}
	

}

