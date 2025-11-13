package com.panchospring.controller;

import com.panchospring.model.dto.cuidado.CuidadoDto;
import com.panchospring.model.entity.Cuidado;
import com.panchospring.service.CuidadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cuidados")
@RequiredArgsConstructor
public class CuidadoController {
    private final CuidadoService service;

    @GetMapping
    public ResponseEntity<List<CuidadoDto>> getCuidados() {
        return ResponseEntity.ok(service.getCuidados());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuidadoDto> getCuidadoById(@PathVariable int id) {
        return ResponseEntity.ok(service.getCuidadoById(id));
    }

    @PostMapping
    public ResponseEntity<CuidadoDto> crearCuidado(@RequestBody Cuidado cuidado) {
        CuidadoDto saved = service.crearCuidado(cuidado);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.id()).toUri();
        return ResponseEntity.created(location).body(saved);
    }
}
