package br.com.apiposto.form;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.github.gilbertotorrezan.viacep.se.ViaCEPClient;
import com.github.gilbertotorrezan.viacep.shared.ViaCEPEndereco;
import com.google.maps.errors.ApiException;

import br.com.apiposto.modelo.Posto;
import br.com.apiposto.modelo.Ubicacion;
import br.com.apiposto.repository.PostoRepository;
import br.com.apiposto.service.imp.GeolocalizacaoService;

public class PostoForm {

	@NotNull(message = "Nome Null")
	@NotEmpty(message = "Nome Empty")
	private String nome;
	@NotNull(message = "Cep Null")
	@NotEmpty(message = "Cep Empty")
	@Size(min = 8, max = 9, message = "Cep inválido no Brasil")
	private String cep;
	@NotNull(message = "Endereço Null")
	@NotEmpty(message = "Endereço Empty")
	private String endereco;
	private static Random idR = new Random();

	public PostoForm(String nome, String cep, String endereco) {
		this.nome = nome;
		this.cep = cep;
		this.endereco = endereco;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Posto converterPosto(PostoForm form, GeolocalizacaoService geolocalizacaoService,
			PostoRepository postoRepository) {
		Posto posto = new Posto();
		Long id = idR.nextLong();
		System.out.println("ID " + id + " REGISTRADOS");
		posto.setId(id);
		posto.setNome(form.getNome());
		Ubicacion ubicacion = new Ubicacion();
		posto.setUbicacion(ubicacion);
		posto.getUbicacion().setId(id);

		String DatosEndereco = null;
		ViaCEPClient cliente = new ViaCEPClient();

		try {
			ViaCEPEndereco enderecoCep = cliente.getEndereco(form.getCep());
			DatosEndereco = enderecoCep.getLogradouro() + " " + enderecoCep.getComplemento() + ", "
					+ enderecoCep.getUf() + ", " + enderecoCep.getBairro();
		} catch (IOException e) {
			System.out.println("Error IO");
			e.getMessage();
		}

		catch (NullPointerException e) {
			String error = "CEP ivalido";
			System.out.println(error);
			e.getMessage();
		}

		posto.getUbicacion().setCep(form.getCep());
		posto.getUbicacion().setEndereco(form.getEndereco());

		System.out.println("Convirtiendo");

		String optenerCordenadasPosto = optenerCordenadasPosto(posto, geolocalizacaoService, DatosEndereco);

		if (optenerCordenadasPosto == null) {
			return posto;
		}
		return null;
	}

	private String optenerCordenadasPosto(Posto posto, GeolocalizacaoService geolocalizacaoService,
			String DatosEndereco) {
		try {
			List<Double> latElong = geolocalizacaoService.obterLatELong(posto.getUbicacion(), DatosEndereco);
			posto.getUbicacion().setCoordinates(latElong);

			System.out.println(posto.getUbicacion().getCoordinates());
			return null;

		} catch (ApiException e) {
			String error = ("API Exception");
			System.out.println(e.getMessage());
			return error;

		} catch (InterruptedException e) {
			String error = ("Interrupted Exception");
			System.out.println(e.getMessage());
			return error;

		} catch (IOException e) {
			System.out.println(e.getMessage());
			String error = "IO Exception";
			return error;

		} catch (ArrayIndexOutOfBoundsException e) {
			String error = "Endereco nao encontrado";
			System.out.println(e.getMessage());
			return error;
		}
	}

}