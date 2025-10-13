package com.panchospring.controller;

import com.panchospring.model.entity.Cuidado;
import com.panchospring.service.CuidadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cuidados")
@RequiredArgsConstructor
public class CuidadoController {
    private final CuidadoService service;

    @GetMapping
    public ResponseEntity<List<Cuidado>> getCuidados() {
        return service.getCuidados();
    }

    @PostMapping
    public ResponseEntity<Cuidado> crearCuidado(@RequestBody Cuidado cuidado) {
        throw new UnsupportedOperationException();
    }
}
