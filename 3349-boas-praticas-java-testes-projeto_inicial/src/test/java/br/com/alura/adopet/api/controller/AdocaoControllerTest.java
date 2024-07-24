package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class AdocaoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void deveDevolverCodigo400ParaSolicitacaoDeAdocaoComErros() throws Exception {
        // Arrange
        String json = "{}";

        // ACT
        var response = mvc.perform(
                post("/adocoes")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        // ASSERT
        Assertions.assertEquals(400, response.getStatus());
    }
}