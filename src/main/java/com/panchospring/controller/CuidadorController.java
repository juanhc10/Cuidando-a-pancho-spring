package com.panchospring.controller;

import com.panchospring.model.dto.cuidador.CuidadorDto;
import com.panchospring.model.dto.mascota.MascotaDto;
import com.panchospring.service.CuidadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/cuidadores")
@RequiredArgsConstructor
public class CuidadorController {
    private final CuidadorService service;

    @GetMapping
    public ResponseEntity<List<CuidadorDto>> getCuidadores() {
        return ResponseEntity.ok(service.getCuidadores());
    }

    @PatchMapping("/{nombre}/acreditacion")
    public ResponseEntity<CuidadorDto> habilitarCuidadoExotica(@PathVariable String nombre) {
        return ResponseEntity.ok(service.habilitarCuidadoExotica(nombre));
    }

    @PutMapping("/{nombre}/actualizar")
    public ResponseEntity<CuidadorDto> actualizarCuidador(@PathVariable String nombre, @RequestBody CuidadorDto cuidadorBody) {
        return ResponseEntity.ok(service.actualizarCuidador(nombre, cuidadorBody));
    }

    @PatchMapping("add/{nombreCuidador}/{idMascota}")
    public ResponseEntity<Set<MascotaDto>> aniadirMascotaFavorita(@PathVariable String nombreCuidador, @PathVariable int idMascota) {
        return ResponseEntity.ok(service.aniadirMascotaFavorita(nombreCuidador, idMascota));
    }

    @PatchMapping("eliminar/{nombreCuidador}/{idMascota}")
    public ResponseEntity<Set<MascotaDto>> eliminarMascotaFavorita(@PathVariable String nombreCuidador, @PathVariable int idMascota) {
        return ResponseEntity.ok(service.eliminarMascotaFavorita(nombreCuidador, idMascota));
    }

    @GetMapping("/favoritas/{nombreCuidador}")
    public ResponseEntity<Set<MascotaDto>> getFavoritas(@PathVariable String nombreCuidador) {
        return ResponseEntity.ok(service.getFavoritas(nombreCuidador));
    }

    @PatchMapping("/premio/{nombreCuidador}/{idPremio}")
    public ResponseEntity<Integer> canjearPremio(@PathVariable String nombreCuidador, @PathVariable int idPremio) {
        return ResponseEntity.ok(service.canjearPremio(nombreCuidador, idPremio));
    }
}
