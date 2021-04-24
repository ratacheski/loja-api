package com.adalberto.loja.api.model.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ClienteInput {

	@NotBlank
	@Size(max = 255)
	private String nome;

	@NotBlank
	@Size(max = 11)
	private String cpf;

	@NotBlank
	@Size(max = 255)
	private String senha;

	@Size(max = 255)
	@Email
	private String email;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
