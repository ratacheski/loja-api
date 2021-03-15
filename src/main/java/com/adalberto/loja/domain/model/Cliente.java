package com.adalberto.loja.domain.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Cliente {
	
	@Column(name = "nome")
	@NotBlank
	@Size(max = 255)
	private String nome;
	@Column(name = "cpf")
	@NotBlank
	@Size(max = 11)
	private String cpf;
	@Column(name = "email")
	@Size(max = 255)
	@Email
	private String email;
	@Column(name = "senha")
	@NotBlank
	@Size(max = 255)
	private String senha;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	public Cliente() {
		super();

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Cliente other = (Cliente) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", cpf=" + cpf + ", email=" + email + ", senha=" + senha + ", id=" + id + "]";
	}

	public Cliente(String nome, String cpf, String email, String senha, int id) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		this.id = id;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;

	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
