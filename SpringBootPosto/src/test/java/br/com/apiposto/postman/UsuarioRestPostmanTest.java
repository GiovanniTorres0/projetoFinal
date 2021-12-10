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

import br.com.apiposto.basetest.Json;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class UsuarioRestPostmanTest extends Json {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void deveriaDevolver201PorCriacaoDeDados() throws Exception {
		URI uri;
		uri = new URI("/usuario/save");

		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(jsonU).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(201));

	}

	@Test
	public void deveriaBuscarPorTodosId() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/usuario/all")).andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	public void deveriaBuscarPorId() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/usuario/find" + idB))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	public void deletarPorId() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/usuario/delete" + idD))
				.andExpect(MockMvcResultMatchers.status().is(204));
	}

}
