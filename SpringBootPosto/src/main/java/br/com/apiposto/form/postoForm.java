package br.com.apiposto.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class postoForm {
	
	@NotNull
	@NotEmpty
	private String nome;
	
	@NotNull
	@NotEmpty
	private String ubicacion;

	public postoForm() {
	}

	public postoForm(String nome, String ubicacion) {
		this.nome = nome;
		this.ubicacion = ubicacion;
	}

	public String getNome() {
		return nome;
	}

	public String getUbicacion() {
		return ubicacion;
	}

}
