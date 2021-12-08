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
public class PostoRestPostmanTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void deveriaDevolver400PorInclusaoErradaDeDados() throws Exception {
		URI uri;
		uri = new URI("/posto/save");

		String json = "{\"id:12345\",\"nome\":\"\",\"ubicacion\":\"null\"}";

		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));

	}

	@Test
	public void deveriaDevolver201PorCriacaoDeDados() throws Exception {
		URI uri;
		uri = new URI("/posto/save");
		
		
		String json = "{\"nome\":\"cabure\",\"endereco\":\"Rua Andradas\",\"cep\":\"97573410\"}";

		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(201));

	}

	@Test
	public void deveriaBuscarPorTodosId() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/posto/all")).andExpect(MockMvcResultMatchers.status().is(200));

	}
	
	@Test
	public void deveriaBuscarPorId() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/posto/find" + "/1"))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	//Como a id é gerada automaticamente ele da erro 204 [No Content] caso tenha colocado uma id que não existe no banco
	@Test
	public void deletarPorId() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/posto/delete" + "/100"))
		.andExpect(MockMvcResultMatchers.status().is(204));
	}
	
	
	
	

}
