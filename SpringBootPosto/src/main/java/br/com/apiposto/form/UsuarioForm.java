package br.com.apiposto.form;

import java.io.IOException;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.github.gilbertotorrezan.viacep.se.ViaCEPClient;
import com.github.gilbertotorrezan.viacep.shared.ViaCEPEndereco;

import br.com.apiposto.modelo.Ubicacion;
import br.com.apiposto.modelo.Usuario;

public class UsuarioForm {

	@NotNull(message = "Id Null")
	private Long id;
	@NotNull(message = "Nome Null")
	@NotEmpty(message = "Nome Empty")
	private String nome;
	@NotNull(message = "Cep Null")
	@NotEmpty(message = "Cep Empty")
	private String cep;
	@NotNull(message = "Senha Null")
	@NotEmpty(message = "Senha Empty")
	private String senha;
	@NotNull(message = "Email Null")
	@NotEmpty(message = "Email Empty")
	@Email(message = "Your Email is not valid")
	private String email;
	@NotNull(message = "Id Null")
	@NotEmpty(message = "Id Empty")
	private String endereco;
	private Long idUbicacion;

	
	
	public UsuarioForm() {
	}

	public UsuarioForm(Long id, String nome, String cep, String senha, String email, String endereco,
			Long idUbicacion) {
		this.id = id;
		this.nome = nome;
		this.cep = cep;
		this.senha = senha;
		this.email = email;
		this.endereco = endereco;
		this.idUbicacion = idUbicacion;
	}

	public Long getIdUbicacion() {
		return idUbicacion;
	}

	public void setIdUbicacion(Long idUbicacion) {
		this.idUbicacion = idUbicacion;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
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

	public Usuario converter(UsuarioForm form) {
		Usuario usuario = new Usuario();
		usuario.setId(form.getId());
		usuario.setNome(form.getNome());
		usuario.setEmail(form.getEmail());
		Ubicacion ubicacion = new Ubicacion();
		usuario.setUbicacion(ubicacion);
		usuario.getUbicacion().setId((long) 1);

		ViaCEPClient cliente = new ViaCEPClient();
		try {
			ViaCEPEndereco enderecoCep = cliente.getEndereco(form.getCep());
			System.out.println(enderecoCep.getLogradouro() + " " + enderecoCep.getComplemento() + ", "
					+ enderecoCep.getUf() + ", " + enderecoCep.getBairro() + ", " + enderecoCep.getIbge());
		} catch (IOException e) {
			e.printStackTrace();
		}

		String encode = new BCryptPasswordEncoder().encode(form.getSenha());
		usuario.setSenha(encode);

		usuario.getUbicacion().setCep(form.getCep());
		usuario.getUbicacion().setEndereco(form.getEndereco());

		System.out.println("Convirtiendo");

		return usuario;
	}

}
