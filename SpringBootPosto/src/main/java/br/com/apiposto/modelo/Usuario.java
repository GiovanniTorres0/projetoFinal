package br.com.apiposto.modelo;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usuarios")
public class Usuario {

	@Id
	private ObjectId id;
	private String nome;
	private Ubicacion ubicacion;

	public Usuario() {
	}

	public Usuario(String nome, Ubicacion ubicacion) {
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

	public void setUbicacion(Ubicacion gps) {
		this.ubicacion = gps;
	}

	public Usuario criarId() {
		setId(new ObjectId());
		return this;
	}


	

}