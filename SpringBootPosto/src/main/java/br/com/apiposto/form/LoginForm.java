package br.com.apiposto.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {
	@NotEmpty(message = "Email Empty") @NotNull(message = "Email Null") @Email(message = "Your Email is not valid")
	private String email;
	
	@NotEmpty(message = "Senha Empty") @NotNull(message = "Senha Null")
	private String senha;

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

	public UsernamePasswordAuthenticationToken coverter() {
		
		return new UsernamePasswordAuthenticationToken(email, senha);
	}

}
