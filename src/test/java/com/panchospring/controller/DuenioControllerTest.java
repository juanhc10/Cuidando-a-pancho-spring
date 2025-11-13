package com.panchospring.controller;

import com.panchospring.model.dto.cuidador.CuidadorDto;
import com.panchospring.model.dto.duenio.DuenioDto;
import com.panchospring.service.DuenioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DuenioController.class)
class DuenioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DuenioService duenioService;

    @Test
    @DisplayName("Debe devolver la lista de dueños")
    void getDuenios() throws Exception {
        DuenioDto duenioDto = DuenioDto.builder().nombre("juan").build();
        Mockito.when(duenioService.getDuenios()).thenReturn(List.of(duenioDto));

        mockMvc.perform(get("/api/v1/duenios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("juan"));
    }

    @Test
    @DisplayName("Debe añadir cuidador favorito")
    void aniadirCuidadorFavorita() throws Exception {
        CuidadorDto cuidadorDto = CuidadorDto.builder().nombre("maria").build();
        Mockito.when(duenioService.aniadirCuidadorFavorita("juan", 1))
                .thenReturn(Set.of(cuidadorDto));

        mockMvc.perform(patch("/api/v1/duenios/add/juan/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("maria"));
    }

    @Test
    @DisplayName("Debe eliminar cuidador favorito")
    void eliminarCuidadorFavorita() throws Exception {
        CuidadorDto cuidadorDto = CuidadorDto.builder().nombre("maria").build();
        Mockito.when(duenioService.eliminarCuidadorFavorita("juan", 1))
                .thenReturn(Set.of(cuidadorDto));

        mockMvc.perform(patch("/api/v1/duenios/eliminar/juan/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("maria"));
    }

    @Test
    @DisplayName("Debe devolver cuidadores favoritos")
    void getFavoritas() throws Exception {
        CuidadorDto cuidadorDto = CuidadorDto.builder().nombre("maria").build();
        Mockito.when(duenioService.getFavoritas("juan"))
                .thenReturn(Set.of(cuidadorDto));

        mockMvc.perform(get("/api/v1/duenios/favoritas/juan"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("maria"));
    }
}
