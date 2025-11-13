package com.panchospring.controller;

import com.panchospring.model.dto.usuario.UsuarioLogin;
import com.panchospring.model.dto.usuario.UsuarioRegistro;
import com.panchospring.model.dto.usuario.UsuarioResponse;
import com.panchospring.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService service;

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> getUsuarios() {
        return ResponseEntity.ok(service.getUsuarios());
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioResponse> getUsuario(@PathVariable int idUsuario) {
        return ResponseEntity.ok(service.getUsuario(idUsuario));
    }

    @GetMapping("/login")
    public ResponseEntity<UsuarioResponse> login(@RequestBody @Validated UsuarioLogin usuario) {
        return ResponseEntity.ok(service.login(usuario));
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> registrarUsuario(@RequestBody @Validated UsuarioRegistro usuario) {
        UsuarioResponse creado = service.registrarUsuario(usuario);
        URI location = URI.create("/api/v1/usuarios/" + creado.id());
        return ResponseEntity.created(location).body(creado);
    }

    @PatchMapping("/{nombre}")
    public ResponseEntity<String> cambiarIdioma(@PathVariable String nombre, @RequestParam String idioma) {
        service.cambiarIdioma(nombre, idioma);
        return ResponseEntity.ok("Idioma cambiado a " + idioma);
    }
}
