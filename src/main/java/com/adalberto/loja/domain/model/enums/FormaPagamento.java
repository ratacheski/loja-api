package com.adalberto.loja.domain.model.enums;

import java.util.stream.Stream;

import com.adalberto.loja.domain.exceptions.RegraDeNegocioException;

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

	public static FormaPagamento forma(int valor) {

		FormaPagamento[] formas = FormaPagamento.values();
		Stream<FormaPagamento> stream = Stream.of(formas);
		stream = stream.filter(forma -> forma.getValor() == valor);
		return stream.findFirst().orElseThrow(() -> new RegraDeNegocioException("Forma inexistente"));
	}

}
