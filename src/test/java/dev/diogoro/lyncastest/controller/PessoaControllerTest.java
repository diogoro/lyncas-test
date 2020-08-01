package dev.diogoro.lyncastest.controller;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.diogoro.lyncastest.model.PessoaDto;
import dev.diogoro.lyncastest.service.PessoaService;

@WebMvcTest(PessoaController.class)
class PessoaControllerTest extends BaseTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	PessoaService pessoaService;

	@Test
	void testObterPessoaPorId() throws Exception {
		given(pessoaService.obterPessoaPorId(any())).willReturn(obterPessoaValida());

		mockMvc.perform(get("/api/v1/pessoa/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void testCadastrarPessoa() throws Exception {
		PessoaDto pessoaDto = obterPessoaValida();
		String pessoaDtoJson = objectMapper.writeValueAsString(pessoaDto);

		given(pessoaService.cadastrarPessoa(any())).willReturn(pessoaDto);

		mockMvc.perform(post("/api/v1/pessoa/").contentType(MediaType.APPLICATION_JSON).content(pessoaDtoJson)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
	}

	@Test
	void testAtualizarPessoa() throws Exception {
		PessoaDto pessoaDto = obterPessoaValida();
		String pessoaDtoJson = objectMapper.writeValueAsString(pessoaDto);

		given(pessoaService.atualizarPessoa(any(), any())).willReturn(pessoaDto);

		mockMvc.perform(put("/api/v1/pessoa/" + UUID.randomUUID().toString()).contentType(MediaType.APPLICATION_JSON)
				.content(pessoaDtoJson)).andExpect(status().isNoContent());
	}
	
	@Test
	void testObterListaPessoas() throws Exception{
		given(pessoaService.obterListaPessoas()).willReturn(obterListaPessoasValidas());

		mockMvc.perform(get("/api/v1/pessoa/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	//@Test //Precisa pensar nesse teste
	void testApagarPessoa() throws Exception {
		UUID randomUUID = UUID.randomUUID();
		
		PessoaService pessoaSpy = Mockito.spy(pessoaService);
		doNothing().when(pessoaSpy).apagarPessoa(randomUUID);
		
		mockMvc.perform(delete("/api/v1/beer/" + randomUUID.toString())).andExpect(status().isNoContent());
		
		verify(pessoaService, times(1)).apagarPessoa(randomUUID);
	}

}
