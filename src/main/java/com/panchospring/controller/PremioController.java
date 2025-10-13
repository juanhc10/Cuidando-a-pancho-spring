package com.panchospring.controller;

import com.panchospring.model.dto.premio.ProductoDto;
import com.panchospring.model.dto.premio.PromocionDto;
import com.panchospring.model.entity.Premio;
import com.panchospring.service.PremioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/premios")
@RequiredArgsConstructor
public class PremioController {
    private final PremioService service;

    @GetMapping
    public ResponseEntity<List<Premio>> getPremios() {
        return service.getPremios();
    }

    @PostMapping("/producto")
    public ResponseEntity<ProductoDto> crearProducto(@Valid @RequestBody ProductoDto producto) {
        return service.crearProducto(producto);
    }

    @PostMapping("/promocion")
    public ResponseEntity<PromocionDto> crearPromocion(@Valid @RequestBody PromocionDto promocion) {
        return service.crearPromocion(promocion);
    }


}
