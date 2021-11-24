package br.com.apiposto.dto;

import org.bson.types.ObjectId;

import br.com.apiposto.modelo.Posto;

public class PostoDto {

	
	private ObjectId id;
	private String nome;

	public PostoDto(Posto posto) {
		this.id = posto.getId();
		this.nome = posto.getNome();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

}
