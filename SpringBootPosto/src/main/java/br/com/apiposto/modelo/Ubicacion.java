package br.com.apiposto.modelo;

import java.util.List;

import org.springframework.data.annotation.Id;




public class Ubicacion {

	@Id
	private Long id;	
	private String endereco;
	private String cep;
	private List<Double> coordinates;
	private String type = "Point";

	public Ubicacion() {
	}

	public Ubicacion(String endereco, List<Double> coordinates, String cep) {
		this.endereco = endereco;
		this.coordinates = coordinates;
		this.cep =cep;
	}
	
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public List<Double> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(List<Double> cordinates) {
		this.coordinates = cordinates;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
