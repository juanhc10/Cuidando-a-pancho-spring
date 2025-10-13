package com.panchospring.service.mapper;

import com.panchospring.model.dto.premio.ProductoDto;
import com.panchospring.model.dto.premio.PromocionDto;
import com.panchospring.model.entity.Producto;
import com.panchospring.model.entity.Promocion;
import org.springframework.stereotype.Service;

@Service
public class PremioMapper {

    public Producto toProducto(ProductoDto premio) {
        return Producto.builder()
                .coste(premio.getCoste())
                .descripcion(premio.getDescripcion())
                .nombre(premio.getNombre())
                .build();
    }

    public Promocion toPromocion(PromocionDto premio) {
        return Promocion.builder()
                .coste(premio.getCoste())
                .horario(premio.getHorario())
                .build();
    }
}
