//package br.com.apiposto.form;
//
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotNull;
//
//import br.com.apiposto.modelo.Ubicacion;
//import br.com.apiposto.modelo.Usuario;
//import br.com.apiposto.repository.UbicacionRepository;
//
//public class UsuarioForm {
//
//	@NotNull
//	@NotEmpty
//	private Long id;
//	@NotNull
//	@NotEmpty
//	private String nome;
//	@NotNull
//	@NotEmpty
//	private String cep;
//	@NotNull
//	@NotEmpty
//	private String senha;
//	@NotNull
//	@NotEmpty
//	private String email;
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getNome() {
//		return nome;
//	}
//
//	public void setNome(String nome) {
//		this.nome = nome;
//	}
//
//	public String getCep() {
//		return cep;
//	}
//
//	public void setCep(String cep) {
//		this.cep = cep;
//	}
//
//	public String getSenha() {
//		return senha;
//	}
//
//	public void setSenha(String senha) {
//		this.senha = senha;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public Usuario converter(UbicacionRepository ubicacionRepository) {
//		Ubicacion ubicacion = ubicacionRepository.findByCep(cep);
//		return new Usuario(id, nome, email, senha, ubicacion);
//	}
//	
//	
//}