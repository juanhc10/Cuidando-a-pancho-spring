package com.panchospring.controller;

import com.panchospring.model.dto.mascota.MascotaDto;
import com.panchospring.model.entity.Cuidador;
import com.panchospring.service.CuidadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/cuidadores")
@RequiredArgsConstructor
public class CuidadorController {
    private final CuidadorService service;

    @GetMapping
    public ResponseEntity<List<Cuidador>> getCuidadores() {
        return service.getCuidadores();
    }

    @PatchMapping("/{nombre}/acreditacion")
    public ResponseEntity<Cuidador> habilitarCuidadoExotica(@PathVariable String nombre) {
        return service.habilitarCuidadoExotica(nombre);
    }

    @PutMapping("/{nombre}/actualizar")
    public ResponseEntity<Cuidador> actualizarCuidador(@PathVariable String nombre, @RequestBody Cuidador cuidadorBody) {
        return service.actualizarCuidador(nombre, cuidadorBody);
    }

    @PatchMapping("add/{nombreCuidador}/{idMascota}")
    public ResponseEntity<Set<MascotaDto>> aniadirMascotaFavorita(@PathVariable String nombreCuidador, @PathVariable int idMascota) {
        return service.aniadirMascotaFavorita(nombreCuidador, idMascota);
    }

    @PatchMapping("eliminar/{nombreCuidador}/{idMascota}")
    public ResponseEntity<Set<MascotaDto>> eliminarMascotaFavorita(@PathVariable String nombreCuidador, @PathVariable int idMascota) {
        return service.eliminarMascotaFavorita(nombreCuidador, idMascota);
    }

    @GetMapping("/favoritas/{nombreCuidador}")
    public ResponseEntity<Set<MascotaDto>> getFavoritas(@PathVariable String nombreCuidador) {
        return service.getFavoritas(nombreCuidador);
    }

    @PatchMapping("/premio/{nombreCuidador}/{idPremio}")
    public ResponseEntity<Integer> canjearPremio(@PathVariable String nombreCuidador, @PathVariable int idPremio) {
        return service.canjearPremio(nombreCuidador, idPremio);
    }
}
