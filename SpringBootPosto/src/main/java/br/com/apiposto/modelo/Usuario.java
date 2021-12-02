package br.com.apiposto.modelo;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Document(collection = "usuarios")
public class Usuario implements UserDetails {

	private static final long serialVersionUID = 1L;
	@Id
	private Long id;
	private String nome;
	private String email;
	private String senha;
	@DBRef
	private Ubicacion ubicacion;
	@DBRef
	private List<Perfil> perfils =new ArrayList<>();
	
	public Usuario() {
	}

	public Usuario(Long id, String nome, String email, String senha, Ubicacion ubicacion) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.ubicacion = ubicacion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.perfils;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}