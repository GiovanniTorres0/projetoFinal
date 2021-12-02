package br.com.apiposto.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.apiposto.modelo.Usuario;

public class UsuarioDto {

	private Long id;
	private String nome;

	public UsuarioDto(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static List<UsuarioDto> converter(List<Usuario> usuarios) {
		return usuarios.stream().map(UsuarioDto::new).collect(Collectors.toList());

	}
}
