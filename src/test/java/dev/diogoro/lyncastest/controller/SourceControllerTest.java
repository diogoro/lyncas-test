package dev.diogoro.lyncastest.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import dev.diogoro.lyncastest.service.SourceService;

@WebMvcTest(SourceController.class)
class SourceControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	SourceService sourceService;

	@Test
	void testObterLinkRepositorio() throws Exception {
		String url = "https://github.com/diogoro/lyncas-test";
		given(sourceService.obterLinkRepositorio()).willReturn(url);
		
		mockMvc.perform(get("/source").accept(MediaType.TEXT_PLAIN_VALUE)).andExpect(status().isOk());
	}

}
