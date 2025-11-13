package com.panchospring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.panchospring.model.dto.usuario.UsuarioLogin;
import com.panchospring.model.dto.usuario.UsuarioRegistro;
import com.panchospring.model.dto.usuario.UsuarioResponse;
import com.panchospring.service.UsuarioService;
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

@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Debe devolver la lista de usuarios")
    void getUsuarios() throws Exception {
        UsuarioResponse usuarioResponse = new UsuarioResponse(1, "juan", "123", com.panchospring.model.entity.Idioma.ESPANIOL);
        Mockito.when(usuarioService.getUsuarios())
                .thenReturn(org.springframework.http.ResponseEntity.ok(List.of(usuarioResponse)));

        mockMvc.perform(get("/api/v1/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("juan"));
    }

    @Test
    @DisplayName("Debe devolver un usuario por id")
    void getUsuario() throws Exception {
        UsuarioResponse usuarioResponse = new UsuarioResponse(1, "juan", "123", com.panchospring.model.entity.Idioma.ESPANIOL);
        Mockito.when(usuarioService.getUsuario(1))
                .thenReturn(org.springframework.http.ResponseEntity.ok(usuarioResponse));

        mockMvc.perform(get("/api/v1/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("juan"));
    }

    @Test
    @DisplayName("Debe registrar un usuario")
    void registrarUsuario() throws Exception {
        UsuarioRegistro usuarioRegistro = new UsuarioRegistro("juan", "123", com.panchospring.model.entity.Idioma.ESPANIOL, "duenio");
        UsuarioResponse usuarioResponse = new UsuarioResponse(1, "juan", "123", com.panchospring.model.entity.Idioma.ESPANIOL);
        Mockito.when(usuarioService.registrarUsuario(any(UsuarioRegistro.class)))
                .thenReturn(org.springframework.http.ResponseEntity.ok(usuarioResponse));

        mockMvc.perform(post("/api/v1/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuarioRegistro)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("juan"));
    }

    @Test
    @DisplayName("Debe hacer login")
    void login() throws Exception {
        UsuarioLogin usuarioLogin = new UsuarioLogin("juan", "123");
        UsuarioResponse usuarioResponse = new UsuarioResponse(1, "juan", "123", com.panchospring.model.entity.Idioma.ESPANIOL);
        Mockito.when(usuarioService.login(any(UsuarioLogin.class)))
                .thenReturn(org.springframework.http.ResponseEntity.ok(usuarioResponse));

        mockMvc.perform(get("/api/v1/usuarios/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuarioLogin)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("juan"));
    }

    @Test
    @DisplayName("Debe cambiar el idioma")
    void cambiarIdioma() throws Exception {
        Mockito.when(usuarioService.cambiarIdioma("juan", "ingles"))
                .thenReturn(org.springframework.http.ResponseEntity.ok("Idioma cambiado a ingles"));

        mockMvc.perform(patch("/api/v1/usuarios/juan?idioma=ingles"))
                .andExpect(status().isOk())
                .andExpect(content().string("Idioma cambiado a ingles"));
    }
}
