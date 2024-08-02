package com.iannovais.lembrete;

import com.iannovais.lembrete.controller.LembreteController;
import com.iannovais.lembrete.entity.Lembrete;
import com.iannovais.lembrete.service.LembreteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@WebFluxTest(LembreteController.class)
public class LembreteControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private LembreteService lembreteService;

    private Lembrete lembrete;

    @BeforeEach
    void setUp() {
        lembrete = new Lembrete();
        lembrete.setID(1L);
        lembrete.setNome("Estudos");
        lembrete.setData(LocalDate.of(2024, 8, 4));
    }

    @Test
    void testCriarLembreteComSucesso() {
        Lembrete lembreteCriado = new Lembrete();
        lembreteCriado.setID(1L);
        lembreteCriado.setNome("Reunião de Projetos");
        lembreteCriado.setData(LocalDate.of(2024, 7, 31));

        when(lembreteService.criar(lembrete)).thenReturn(Arrays.asList(lembreteCriado));

        webTestClient.post()
            .uri("/lembretes")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(lembrete)
            .exchange()
            .expectStatus().isOk()
            .expectBody();
    }
    
    @Test
    void testListarLembretes() {
        when(lembreteService.listar()).thenReturn(Arrays.asList(lembrete));

        webTestClient.get()
            .uri("/lembretes")
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$[0].nome").isEqualTo("Estudos")
            .jsonPath("$[0].data").isEqualTo("2024-08-04")
            .jsonPath("$[0].id").isEqualTo(1);
    }

    @Test
    void testListarPorDataAgrupada() {
        Map<String, List<Lembrete>> groupedLembretes = new HashMap<>();
        groupedLembretes.put(LocalDate.of(2024, 8, 2).toString(), Arrays.asList(lembrete));
        when(lembreteService.listarPorGrupo()).thenReturn(groupedLembretes);

        webTestClient.get()
            .uri("/lembretes/agrupados")
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$['2024-08-02'][0].nome").isEqualTo("Estudos")
            .jsonPath("$['2024-08-02'][0].data").isEqualTo("2024-08-04")
            .jsonPath("$['2024-08-02'][0].id").isEqualTo(1);
    }

    @Test
    void testDeletarLembrete() {
        when(lembreteService.deletar(1L)).thenReturn(Arrays.asList());

        webTestClient.delete()
            .uri("/lembretes/1")
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$").isEmpty();
    }
}