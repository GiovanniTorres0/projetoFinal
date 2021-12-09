package br.com.apiposto.form;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.github.gilbertotorrezan.viacep.se.ViaCEPClient;
import com.github.gilbertotorrezan.viacep.shared.ViaCEPEndereco;
import com.google.maps.errors.ApiException;

import br.com.apiposto.modelo.Ubicacion;
import br.com.apiposto.modelo.Usuario;
import br.com.apiposto.repository.UsuarioRepository;
import br.com.apiposto.service.imp.GeolocalizacaoService;

public class UsuarioForm {

	@NotNull(message = "Nome Null")
	@NotEmpty(message = "Nome Empty")
	private String nome;

	@NotNull(message = "Cep Null")
	@NotEmpty(message = "Cep Empty")
	@Size(min = 8, max = 9, message = "Cep inválido no Brasil")
	private String cep;

	@NotNull(message = "Senha Null")
	@NotEmpty(message = "Senha Empty")
	private String senha;

	@NotNull(message = "Email Null")
	@NotEmpty(message = "Email Empty")
	@Email(message = "Your Email is not valid")
	private String email;

	@NotNull(message = "logradoro Null")
	@NotEmpty(message = "logradoro Empty")
	@Size(min = 3, message = "logradoro no valido")
	private String logradoro;

	@NotNull(message = "cidade Null")
	@NotEmpty(message = "cidade Empty")
	private String cidade;

	@NotNull(message = "UF Null")
	@NotEmpty(message = "UF Empty")
	private String uF;

	@NotEmpty(message = "Numero Empty")
	@NotNull(message = "Numero Null")
	@Min(value = 0)
	@Max(value = 9999)
	private String numero;

	private Long idUbicacion;
	private static Random idR = new Random();

	public UsuarioForm() {
	}

	public UsuarioForm(String nome, String cep, String senha, String email, String endereco, Long idUbicacion,
			String cidade, String uF, String numero) {

		this.nome = nome;
		this.cep = cep;
		this.senha = senha;
		this.email = email;
		this.logradoro = endereco;
		this.cidade = cidade;
		this.uF = uF;
		this.numero = numero;
		this.idUbicacion = idUbicacion;
	}

	public Long getIdUbicacion() {
		return idUbicacion;
	}

	public void setIdUbicacion(Long idUbicacion) {
		this.idUbicacion = idUbicacion;
	}

	public String getLogradoro() {
		return logradoro;
	}

	public void setLogradoro(String endereco) {
		this.logradoro = endereco;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Usuario converter(UsuarioForm form, GeolocalizacaoService geolocalizacaoService,
			UsuarioRepository usuarioRepository) {

		String[] Ufs = { "AC","AL","AM","AP","BA","CE","DF","ES","GO","MA","MT","MS","MG","PA","PB","PR","PE","PI","RJ","RN","RS","RO","RR","SC","SP","SE","TO"};

		List<String> UFsList = Arrays.asList(Ufs);
		if (!UFsList.contains(form.getuF())) {
			System.out.println("UF IVALIDO");
			return null;
		}

		Usuario usuario = new Usuario();
		Long id = idR.nextLong();
		System.out.println("ID " + id + " REGISTRADOS");
		usuario.setId(id);
		usuario.setNome(form.getNome());
		Ubicacion ubicacion = new Ubicacion();
		usuario.setUbicacion(ubicacion);
		usuario.getUbicacion().setId(id);

		if (form.isNotPresentEmail(form.getEmail(), usuarioRepository)) {
			usuario.setEmail(form.getEmail());
		} else {
			return null;
		}

		String DatosEndereco = null;
		ViaCEPClient cliente = new ViaCEPClient();

		try {
			ViaCEPEndereco enderecoCep = cliente.getEndereco(form.getCep());

			System.out.println(enderecoCep.getLocalidade().contains(form.getLogradoro()));

			if (form.getCidade().equalsIgnoreCase(enderecoCep.getLocalidade())
					&& form.getuF().equalsIgnoreCase(enderecoCep.getUf())) {

				DatosEndereco = enderecoCep.getComplemento() + ", " + enderecoCep.getUf() + ", "
						+ enderecoCep.getLocalidade();
				System.out.println(DatosEndereco);
			}

			else {
				System.out.println("DADOS DE INDERESO INVALIDOS");
				System.out.println(form.getCidade() + " " + enderecoCep.getLocalidade() + " " + form.getuF() + " "
						+ enderecoCep.getUf() + " " + form.getLogradoro() + " " + enderecoCep.getLogradouro());
				return null;
			}

		} catch (IOException e) {
			System.out.println("Error IO");
			e.getMessage();
			return null;

		}

		catch (NullPointerException e) {
			String error = "CEP ivalido";
			System.out.println(error);
			e.getMessage();
			return null;

		}

		String encode = new BCryptPasswordEncoder().encode(form.getSenha());
		usuario.setSenha(encode);

		usuario.getUbicacion().setCep(form.getCep());
		usuario.getUbicacion().setEndereco(form.getLogradoro());

		System.out.println("Convirtiendo");

		String optenerCordenadas = optenerCordenadasUsuario(usuario, geolocalizacaoService, DatosEndereco);

		if (optenerCordenadas == null) {
			return usuario;
		}
		return null;
	}

	private boolean isNotPresentEmail(String email, UsuarioRepository usuarioRepository) {
		Optional<Usuario> ValidacionDeEmail = usuarioRepository.findByEmail(email);
		if (ValidacionDeEmail.isPresent()) {
			return false;

		}
		return true;

	}

	private String optenerCordenadasUsuario(Usuario usuario, GeolocalizacaoService geolocalizacaoService,
			String DatosEndereco) {
		try {
			List<Double> latElong = geolocalizacaoService.obterLatELong(usuario.getUbicacion(), DatosEndereco);
			usuario.getUbicacion().setCoordinates(latElong);

			System.out.println(usuario.getUbicacion().getCoordinates());
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
			String error = "Indereco nao encontrado";
			System.out.println(e.getMessage());
			return error;
		}
	}

}
