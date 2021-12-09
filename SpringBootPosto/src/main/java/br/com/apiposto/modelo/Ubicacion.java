package br.com.apiposto.modelo;

import java.util.List;

import org.springframework.data.annotation.Id;




public class Ubicacion {

	@Id
	private Long id;	
	private String endereco;
	private String cep;
	private String cidade;
	private String uF;
	private String numero;
	private List<Double> coordinates;
	private String type = "Point";

	public Ubicacion() {
	}

	public Ubicacion(String endereco, List<Double> coordinates, String cep, String cidade , String uF, String numero) {
		this.endereco = endereco;
		this.coordinates = coordinates;
		this.cep =cep;
		this.cidade=cidade;
		this.uF=uF;
		this.numero=numero;
	}
	
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getuF() {
		return uF;
	}

	public void setuF(String uF) {
		this.uF = uF;
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
