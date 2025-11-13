package com.panchospring.controller;

import com.panchospring.model.dto.cuidador.CuidadorDto;
import com.panchospring.model.dto.mascota.MascotaDto;
import com.panchospring.service.CuidadorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CuidadorController.class)
class CuidadorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CuidadorService cuidadorService;

    @Test
    @DisplayName("Debe devolver la lista de cuidadores")
    void getCuidadores() throws Exception {
        CuidadorDto cuidadorDto = CuidadorDto.builder().nombre("maria").build();
        Mockito.when(cuidadorService.getCuidadores())
                .thenReturn(List.of(cuidadorDto));

        mockMvc.perform(get("/api/v1/cuidadores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("maria"));
    }

    @Test
    @DisplayName("Debe devolver las mascotas favoritas de un cuidador")
    void getFavoritas() throws Exception {
        MascotaDto mascotaDto = new MascotaDto("Pedro", "Madrid", "Perro", LocalDateTime.now(), "1234", 5678, 1, "normal");
        Mockito.when(cuidadorService.getFavoritas("maria"))
                .thenReturn(Set.of(mascotaDto));

        mockMvc.perform(get("/api/v1/cuidadores/favoritas/maria"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Pedro"));
    }
}
