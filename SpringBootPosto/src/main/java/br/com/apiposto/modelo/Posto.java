package br.com.apiposto.modelo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "postos")
public class Posto {

	
	private ObjectId id;
	private String nome;
	@DBRef
	private Ubicacion ubicacion;

	public Posto() {

	}

	public Posto(String nome, Ubicacion ubicacion) {
		this.nome = nome;
		this.ubicacion = ubicacion;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
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

	public Posto criarId() {
		setId(new ObjectId());
		return this;
	}

	
	
	
	
}