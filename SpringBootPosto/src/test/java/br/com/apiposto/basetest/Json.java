package br.com.apiposto.basetest;

public class Json {

	// idD = Id delete, idB = Id Busca
	public String idD = "5762948099856594843";
	public String idB = "-5269156856810315958";

	FormularioPosto formularioPosto = new FormularioPosto();

	FormularioUsuario formularioUsuario = new FormularioUsuario();

	public String jsonP = "{\"nome\":\"" + formularioPosto.getNome() + "\",\"endereco\":\""
			+ formularioPosto.getEndereco() + "\",\"cep\":\"" + formularioPosto.getCep() + "\"}";

	public String jsonU = "{\"nome\":\"" + formularioUsuario.getNome() + "\",\"usuario\":\""
			+ formularioUsuario.getUsuario() + "\",\"senha\":\"" + formularioUsuario.getSenha() + "\",\"endereco\":\""
			+ formularioUsuario.getEndereco() + "\",\"cep\":\"" + formularioUsuario.getCep() + "\",\"email\":\""
			+ formularioUsuario.getEmail() + "\",\"logradoro\":\"" + formularioUsuario.getLogradoro() + "\",\"numero:\""
			+ formularioUsuario.getNumero() + "\",\"cidade:\"" + formularioUsuario.getCidade() + "\",\"uF:\""
			+ formularioUsuario.getUf() + "\"}";

}
