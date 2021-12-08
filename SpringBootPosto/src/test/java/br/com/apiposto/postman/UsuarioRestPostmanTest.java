package br.com.apiposto.postman;

import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class UsuarioRestPostmanTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void deveriaDevolver400PorInclusaoErradaDeDados() throws Exception {
		URI uri;
		uri = new URI("/usuario/save");

		String json = "{\"nome\":\"Giovanni\",\"ubicacion\":\"null\"}";

		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	//Vai dar http 400 caso o registro setado já exista no banco
	@Test
	public void deveriaDevolver201PorCriacaoDeDados() throws Exception {
		URI uri;
		uri = new URI("/usuario/save");

		String json = "{\"nome\":\"Ricardo\",\"usuario\":\"teste123\",\"senha\":\"123456789\",\"endereco\":\"Rua Andradas\",\"cep\":\"97573410\",\"email\":\"teste@example.com\"}";

		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(201));

	}

	@Test
	public void deveriaBuscarPorTodosId() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/usuario/all")).andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	public void deveriaBuscarPorId() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/usuario/find" + "/1"))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	//Como a id é gerada automaticamente ele da erro 204 [No Content] caso tenha colocado uma id que não existe no banco
	@Test
	public void deletarPorId() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/usuario/delete" + "/100"))
		.andExpect(MockMvcResultMatchers.status().is(204));
	}
	
	
	
	
	
	
	
	

}
