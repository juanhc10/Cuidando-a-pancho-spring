package com.panchospring.controller;

import com.panchospring.model.dto.premio.ProductoDto;
import com.panchospring.model.dto.premio.PromocionDto;
import com.panchospring.model.dto.premio.PremioDto;
import com.panchospring.service.PremioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/premios")
@RequiredArgsConstructor
public class PremioController {
    private final PremioService service;

    @GetMapping
    public ResponseEntity<List<PremioDto>> getPremios() {
        return ResponseEntity.ok(service.getPremios());
    }

    @PostMapping("/producto")
    public ResponseEntity<ProductoDto> crearProducto(@Valid @RequestBody ProductoDto producto) {
        ProductoDto creado = service.crearProducto(producto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{nombre}")
                .buildAndExpand(creado.getNombre()).toUri();
        return ResponseEntity.created(location).body(creado);
    }

    @PostMapping("/promocion")
    public ResponseEntity<PromocionDto> crearPromocion(@Valid @RequestBody PromocionDto promocion) {
        PromocionDto creada = service.crearPromocion(promocion);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{coste}")
                .buildAndExpand(creada.getCoste()).toUri();
        return ResponseEntity.created(location).body(creada);
    }
}
