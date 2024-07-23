package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AdocaoControllerTest {

    @Test
    public void deveDevolverCodigo400ParaSolicitacaoDeAdocaoComErros() {
        // Arrange
        String json = "{}";

        // ACT

        // ASSERT
        Assertions.assertEquals(400, ?);
    }
}