package com.panchospring.controller;

import com.panchospring.model.dto.usuario.UsuarioLogin;
import com.panchospring.model.dto.usuario.UsuarioRegistro;
import com.panchospring.model.dto.usuario.UsuarioResponse;
import com.panchospring.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService service;

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> getUsuarios() {
        return service.getUsuarios();
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioResponse> getUsuario(@PathVariable int idUsuario) {
        return service.getUsuario(idUsuario);
    }

    @GetMapping("/login")
    public ResponseEntity<UsuarioResponse> login(@RequestBody @Validated UsuarioLogin usuario) {
        return service.login(usuario);
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> registrarUsuario(@RequestBody @Validated UsuarioRegistro usuario) {
        return service.registrarUsuario(usuario);
    }

    @PatchMapping("/{nombre}")
    public ResponseEntity<String> cambiarIdioma(@PathVariable String nombre, @RequestParam String idioma) {
        return service.cambiarIdioma(nombre, idioma);
    }
}
