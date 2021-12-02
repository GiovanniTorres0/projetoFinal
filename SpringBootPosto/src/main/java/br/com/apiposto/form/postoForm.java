package br.com.apiposto.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class postoForm {

	@NotNull
	@NotEmpty
	private String nome;
	@NotNull
	@NotEmpty
	private String cep;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

}