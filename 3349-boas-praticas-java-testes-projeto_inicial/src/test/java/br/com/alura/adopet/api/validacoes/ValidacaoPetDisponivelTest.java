package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ValidacaoPetDisponivelTest {

    /**
     * Anotação @InjectMocks
     *
     * Objetivo: Criar uma instância da classe que está sendo testada e injetar automaticamente os mocks nas dependências dessa classe.
     *
     * Quando usar: Utilize @InjectMocks quando quiser testar uma classe em si, com suas dependências simuladas sendo injetadas automaticamente.
     */
    @InjectMocks
    private ValidacaoPetDisponivel validacaoPetDisponivel;

    /**
     * Anotação @Mock
     *
     * Objetivo: Criar objetos simulados para testar comportamentos específicos sem depender das implementações reais.
     *
     * Quando usar: Utilize @Mock quando precisar de uma dependência simulada para testar uma classe isoladamente.
     */
    @Mock
    private PetRepository petRepository;

    @Mock
    private Pet pet;

    @Mock
    private SolicitacaoAdocaoDto dto;

    @Test
    void devePermetirSolicitacaoDeAdocaoPet() {

        // ARRANGE
        BDDMockito.given(petRepository.getReferenceById(dto.idPet())).willReturn(pet);
        BDDMockito.given(pet.getAdotado()).willReturn(false);

        // ASSERT + ACT
        Assertions.assertDoesNotThrow(() -> validacaoPetDisponivel.validar(dto));
    }

    @Test
    void naoDevePermetirSolicitacaoDeAdocaoPet() {

        // ARRANGE
        BDDMockito.given(petRepository.getReferenceById(dto.idPet())).willReturn(pet);
        BDDMockito.given(pet.getAdotado()).willReturn(true);

        // ASSERT + ACT
        Assertions.assertThrows(ValidacaoException.class, () -> validacaoPetDisponivel.validar(dto));
    }

}