package com.panchospring.service;

import com.panchospring.model.dto.premio.ProductoDto;
import com.panchospring.model.dto.premio.PromocionDto;
import com.panchospring.model.dto.premio.PremioDto;
import com.panchospring.model.entity.Premio;
import com.panchospring.repository.PremioRepository;
import com.panchospring.repository.ProductoRepository;
import com.panchospring.repository.PromocionRepository;
import com.panchospring.service.mapper.PremioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PremioService {
    private final PremioRepository premioRepository;
    private final ProductoRepository productoRepository;
    private final PromocionRepository promocionRepository;
    private final PremioMapper mapper;

    public List<PremioDto> getPremios() {
        return premioRepository.findAll().stream().map(mapper::toPremioDto).toList();
    }

    @Transactional
    public ProductoDto crearProducto(ProductoDto producto) {
        productoRepository.save(mapper.toProducto(producto));
        return producto;
    }

    @Transactional
    public PromocionDto crearPromocion(PromocionDto promocion) {
        promocionRepository.save(mapper.toPromocion(promocion));
        return promocion;
    }
}
