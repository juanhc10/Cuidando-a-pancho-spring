package com.panchospring.controller;

import com.panchospring.model.dto.mascota.MascotaDto;
import com.panchospring.service.MascotaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mascotas")
@RequiredArgsConstructor
public class MascotaController {
    private final MascotaService service;

    @GetMapping
    public ResponseEntity<List<MascotaDto>> getMascotas() {
        return service.getMascotas();
    }

    @PostMapping
    public ResponseEntity<MascotaDto> crearMascota(@Valid @RequestBody MascotaDto mascota) {
        return service.crearMascota(mascota);
    }

    @DeleteMapping("/{idMascota}")
    public ResponseEntity<String> eliminarMascota(@PathVariable int idMascota) {
        return service.eliminarMascota(idMascota);
    }

    @PutMapping("/{idMascota}")
    public ResponseEntity<MascotaDto> actualizarMascota(@PathVariable int idMascota, @Valid @RequestBody MascotaDto mascotaBody) {
        return service.actualizarMascota(idMascota, mascotaBody);
    }

}
