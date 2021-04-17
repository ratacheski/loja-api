package com.adalberto.loja.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Problema {

	private Integer status;
	private LocalDateTime datahora;
	private String titulo;
	@JsonInclude(Include.NON_NULL)
	private List<Campo> campos;
	
	public static class Campo{
		private String campo;
		private String mensagem;
		
		
		
		public Campo(String campo, String mensagem) {
			super();
			this.campo = campo;
			this.mensagem = mensagem;
		}
		public String getCampo() {
			return campo;
		}
		public void setCampo(String campo) {
			this.campo = campo;
		}
		public String getMensagem() {
			return mensagem;
		}
		public void setMensagem(String mensagem) {
			this.mensagem = mensagem;
		}
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public LocalDateTime getDatahora() {
		return datahora;
	}
	public void setDatahora(LocalDateTime datahora) {
		this.datahora = datahora;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public List<Campo> getCampos() {
		return campos;
	}
	public void setCampos(List<Campo> campos) {
		this.campos = campos;
	}
	
}
