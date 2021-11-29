package br.com.apiposto.modelo;

import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "postos")
public class Posto {

	
	private Long id;
	private String nome;
//	@DBRef
//	private Ubicacion ubicacion;

	public Posto() {

	}

	public Posto(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}



	
	
	
	
}