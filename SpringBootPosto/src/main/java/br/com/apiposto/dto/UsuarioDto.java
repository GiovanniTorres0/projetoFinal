package br.com.apiposto.dto;

import org.bson.types.ObjectId;

import br.com.apiposto.modelo.Usuario;

public class UsuarioDto {

	private ObjectId id;
	private String nome;

	public UsuarioDto(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
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
