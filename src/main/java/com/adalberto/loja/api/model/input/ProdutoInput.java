package com.adalberto.loja.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

public class ProdutoInput {

	@NotBlank
	@Size(max = 255)
	private String nome;
	
	@NotNull
	private double preco;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

}
