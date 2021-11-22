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
	private Gps gps;

	public Posto() {

	}

	public Posto(String id, String nome, Gps gps) {
		this.id = id;
		this.nome = nome;
		this.gps = gps;
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

	public Gps getGps() {
		return gps;
	}

	public void setGps(Gps gps) {
		this.gps = gps;
	}

}