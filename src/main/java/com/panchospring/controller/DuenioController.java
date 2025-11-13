package com.panchospring.controller;

import com.panchospring.model.dto.cuidador.CuidadorDto;
import com.panchospring.model.entity.Duenio;
import com.panchospring.service.DuenioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/duenios")
@RequiredArgsConstructor
public class DuenioController {
    private final DuenioService service;

    @GetMapping
    public ResponseEntity<List<Duenio>> getDuenios() {
        return ResponseEntity.ok(service.getDuenios());
    }

    @PatchMapping("add/{nombreDuenio}/{idCuidador}")
    public ResponseEntity<Set<CuidadorDto>> aniadirCuidadorFavorita(@PathVariable String nombreDuenio, @PathVariable int idCuidador) {
        return service.aniadirCuidadorFavorita(nombreDuenio, idCuidador);
    }

    @PatchMapping("eliminar/{nombreDuenio}/{idCuidador}")
    public ResponseEntity<Set<CuidadorDto>> eliminarCuidadorFavorita(@PathVariable String nombreDuenio, @PathVariable int idCuidador) {
        return service.eliminarCuidadorFavorita(nombreDuenio, idCuidador);
    }

    @GetMapping("/favoritas/{nombreDuenio}")
    public ResponseEntity<Set<CuidadorDto>> getFavoritas(@PathVariable String nombreDuenio) {
        return service.getFavoritas(nombreDuenio);
    }
}
