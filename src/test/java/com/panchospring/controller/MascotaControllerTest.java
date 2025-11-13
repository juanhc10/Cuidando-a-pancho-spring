package com.panchospring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.panchospring.model.dto.mascota.MascotaDto;
import com.panchospring.service.MascotaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MascotaController.class)
class MascotaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MascotaService mascotaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Debe devolver la lista de mascotas")
    void getMascotas() throws Exception {
        MascotaDto mascotaDto = new MascotaDto("Pedro", "Madrid", "Perro", LocalDateTime.now(), "1234", 5678, 1, "normal");
        Mockito.when(mascotaService.getMascotas())
                .thenReturn(org.springframework.http.ResponseEntity.ok(List.of(mascotaDto)));

        mockMvc.perform(get("/api/v1/mascotas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Pedro"));
    }

    @Test
    @DisplayName("Debe crear una mascota")
    void crearMascota() throws Exception {
        MascotaDto mascotaDto = new MascotaDto("Pedro", "Madrid", "Perro", LocalDateTime.now(), "1234", 5678, 1, "normal");
        Mockito.when(mascotaService.crearMascota(any(MascotaDto.class)))
                .thenReturn(org.springframework.http.ResponseEntity.ok(mascotaDto));

        mockMvc.perform(post("/api/v1/mascotas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mascotaDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Pedro"));
    }

    @Test
    @DisplayName("Debe eliminar una mascota")
    void eliminarMascota() throws Exception {
        Mockito.when(mascotaService.eliminarMascota(1))
                .thenReturn(org.springframework.http.ResponseEntity.ok("Mascota eliminada: 1"));

        mockMvc.perform(delete("/api/v1/mascotas/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Mascota eliminada: 1"));
    }

    @Test
    @DisplayName("Debe actualizar una mascota")
    void actualizarMascota() throws Exception {
        MascotaDto mascotaDto = new MascotaDto("Pedro", "Madrid", "Perro", LocalDateTime.now(), "1234", 5678, 1, "normal");
        Mockito.when(mascotaService.actualizarMascota(Mockito.eq(1), any(MascotaDto.class)))
                .thenReturn(org.springframework.http.ResponseEntity.ok(mascotaDto));

        mockMvc.perform(put("/api/v1/mascotas/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mascotaDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Pedro"));
    }
}
