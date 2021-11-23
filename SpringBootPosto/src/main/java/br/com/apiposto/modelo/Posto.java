package br.com.apiposto.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "postos")
public class Posto {

	@Id
	private String id;
	private String nome;
	@DBRef
	private Ubicacion ubicacion;

	public Posto() {

	}

	public Posto(String nome, Ubicacion ubicacion) {
		this.nome = nome;
		this.ubicacion = ubicacion;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

}