package com.panchospring.controller;

import com.panchospring.model.dto.mascota.MascotaDto;
import com.panchospring.service.MascotaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/mascotas")
@RequiredArgsConstructor
public class MascotaController {
    private final MascotaService service;

    @GetMapping
    public ResponseEntity<List<MascotaDto>> getMascotas() {
        return ResponseEntity.ok(service.getMascotas());
    }

    @PostMapping
    public ResponseEntity<MascotaDto> crearMascota(@Valid @RequestBody MascotaDto mascota) {
        MascotaDto creada = service.crearMascota(mascota);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(creada.duenioId()).toUri();
        return ResponseEntity.created(location).body(creada);
    }

    @DeleteMapping("/{idMascota}")
    public ResponseEntity<String> eliminarMascota(@PathVariable int idMascota) {
        service.eliminarMascota(idMascota);
        return ResponseEntity.ok("Mascota eliminada: " + idMascota);
    }

    @PutMapping("/{idMascota}")
    public ResponseEntity<MascotaDto> actualizarMascota(@PathVariable int idMascota, @Valid @RequestBody MascotaDto mascotaBody) {
        return ResponseEntity.ok(service.actualizarMascota(idMascota, mascotaBody));
    }
}
