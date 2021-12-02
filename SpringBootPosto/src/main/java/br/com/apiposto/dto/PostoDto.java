package br.com.apiposto.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.apiposto.modelo.Posto;

public class PostoDto {

	
	private Long id;
	private String nome;

	public PostoDto(Posto posto) {
		this.id = posto.getId();
		this.nome = posto.getNome();
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

	public static List<PostoDto> converter(List<Posto> postos) {
		return postos.stream().map(PostoDto::new).collect(Collectors.toList());
	
}
}
