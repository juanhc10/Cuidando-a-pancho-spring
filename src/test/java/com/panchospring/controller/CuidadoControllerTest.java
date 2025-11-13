package com.panchospring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.panchospring.model.entity.Cuidado;
import com.panchospring.service.CuidadoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CuidadoController.class)
class CuidadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CuidadoService cuidadoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Debe devolver la lista de cuidados")
    void getCuidados() throws Exception {
        Cuidado cuidado = new Cuidado();
        Mockito.when(cuidadoService.getCuidados())
                .thenReturn(org.springframework.http.ResponseEntity.ok(List.of(cuidado)));

        mockMvc.perform(get("/api/v1/cuidados"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Debe crear un cuidado")
    void crearCuidado() throws Exception {
        Cuidado cuidado = new Cuidado();
        cuidado.setId(1);
        Mockito.when(cuidadoService.crearCuidado(any(Cuidado.class)))
                .thenReturn(cuidado);

        mockMvc.perform(post("/api/v1/cuidados")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cuidado)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }
}
