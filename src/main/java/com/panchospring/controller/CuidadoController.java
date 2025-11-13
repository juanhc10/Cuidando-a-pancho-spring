package com.panchospring.controller;

import com.panchospring.model.entity.Cuidado;
import com.panchospring.repository.CuidadoRepository;
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
    private CuidadoRepository cuidadoRepository;

    @GetMapping
    public ResponseEntity<List<Cuidado>> getCuidados() {
        return service.getCuidados();
    }



    @PostMapping
    public ResponseEntity<Cuidado> crearCuidado(@RequestBody Cuidado cuidado) {
        Cuidado saved = service.crearCuidado(cuidado);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(location).body(saved);
    }
}
