package br.com.apiposto.modelo;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "gps")
public class Ubicacion {

	private String endereco;
	private List<Double> coordenadas;
	private String type = "Point";

	public Ubicacion() {
	}

	public Ubicacion(String endereco, List<Double> coordenadas) {
		this.endereco = endereco;
		this.coordenadas = coordenadas;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public List<Double> getCoordenadas() {
		return coordenadas;
	}

	public void setCoordenadas(List<Double> coordenadas) {
		this.coordenadas = coordenadas;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
