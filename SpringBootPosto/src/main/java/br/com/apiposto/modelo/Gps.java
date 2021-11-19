package br.com.apiposto.modelo;

public class Gps {

	private Long latitud;
	private Long longitud;

	public Gps(Long latitud, Long longitud) {
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public Long getLatitud() {
		return latitud;
	}

	public Long getLongitud() {
		return longitud;
	}

}
