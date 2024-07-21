package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.validacoes.ValidacaoSolicitacaoAdocao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AbrigoServiceTest {

    @InjectMocks
    private AdocaoService adocaoService;

    @Mock
    private AdocaoRepository adocaoRepository;

    @Mock
    private PetRepository petRepository;

    @Mock
    private TutorRepository tutorRepository;

    @Mock
    private EmailService emailService;

    /*
     * Spy
     * Um spy, por outro lado, é um objeto real que é usado durante os testes, mas também permite a observação de seu comportamento.
     * Ao contrário dos mocks, os spies mantêm o comportamento original do objeto sendo espionado, a menos que seja especificamente substituído.
     *
     * Ao criar um spy usando o Mockito, você está usando uma instância real do objeto em questão, mas também pode definir comportamentos específicos para seus métodos.
     * Isso significa que o código original do objeto será executado, a menos que você especifique uma substituição para um determinado método.
     *
     * Os spies são úteis quando você deseja testar partes específicas de um objeto real sem perder o comportamento original do objeto.
     * Você pode substituir apenas os métodos relevantes para seus testes, enquanto o restante do objeto continua funcionando normalmente.
     * Os spies também permitem verificar interações reais com o objeto sob teste, como quantas vezes um método foi chamado.
     *
     * Ao utilizar o Mockito para testes automatizados em Java, você pode escolher entre mocks e spies, dependendo das necessidades específicas do seu teste.
     * Ambas as abordagens são úteis e têm seus usos adequados. A escolha entre mock e spy depende do cenário de teste e dos requisitos específicos.
     */
    @Spy
    private List<ValidacaoSolicitacaoAdocao> validacoes = new ArrayList<>();

    /*
     * Mock
     * Um mock é um objeto simulado que substitui um objeto real durante a execução dos testes.
     * Ele é criado usando o Mockito e permite definir comportamentos esperados e verificar interações com o objeto simulado.
     * Os mocks são úteis quando você deseja isolar o código sob teste de suas dependências, fornecendo respostas predefinidas para os métodos chamados durante os testes.
     *
     * Ao usar um mock, você especifica quais métodos do objeto simulado devem ser chamados e qual será a resposta.
     * Por exemplo, você pode configurar um mock para retornar um valor específico quando um determinado método for chamado.
     * Isso permite que você simule cenários diferentes e verifique como o código reage a eles.
     * Além disso, os mocks fornecem métodos para verificar se determinados métodos foram chamados e quantas vezes foram chamados durante a execução do teste.
     */
    @Mock
    private ValidacaoSolicitacaoAdocao validador1;

    @Mock
    private ValidacaoSolicitacaoAdocao validador2;

    @Mock
    private Pet pet;

    @Mock
    private Tutor tutor;

    @Mock
    private Abrigo abrigo;

    private SolicitacaoAdocaoDto dto;

    @Captor
    private ArgumentCaptor<Adocao> adocaoArgumentCaptor;

    @Test
    void deveSalvarAdocaoAoSolicitar() {
        // Arrange
        this.dto = new SolicitacaoAdocaoDto(10l, 20l, "motivo");

        BDDMockito.given(petRepository.getReferenceById(dto.idPet())).willReturn(pet);
        BDDMockito.given(tutorRepository.getReferenceById(dto.idTutor())).willReturn(tutor);

        BDDMockito.given(pet.getAbrigo()).willReturn(abrigo);

        // ACT
        adocaoService.solicitar(dto);

        // ASSERT
//        BDDMockito.then(adocaoRepository).should().save(BDDMockito.any());
        BDDMockito.then(adocaoRepository).should().save(adocaoArgumentCaptor.capture());
        Adocao adocaoSalva = adocaoArgumentCaptor.getValue();

        Assertions.assertEquals(pet, adocaoSalva.getPet());
        Assertions.assertEquals(tutor, adocaoSalva.getTutor());
        Assertions.assertEquals(dto.motivo(), adocaoSalva.getMotivo());

        BDDMockito.verify(adocaoRepository).save(adocaoSalva);
    }

    @Test
    void deveChamarValidadoresDeAdocaoAoSolicitar() {
        // Arrange
        this.dto = new SolicitacaoAdocaoDto(10l, 20l, "motivo");

        BDDMockito.given(petRepository.getReferenceById(dto.idPet())).willReturn(pet);
        BDDMockito.given(tutorRepository.getReferenceById(dto.idTutor())).willReturn(tutor);

        BDDMockito.given(pet.getAbrigo()).willReturn(abrigo);

        validacoes.add(validador1);
        validacoes.add(validador2);

        // ACT
        adocaoService.solicitar(dto);

        // ASSERT
        BDDMockito.then(validador1).should().validar(dto);
        BDDMockito.then(validador2).should().validar(dto);
    }

}