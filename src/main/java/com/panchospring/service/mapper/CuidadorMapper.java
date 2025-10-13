package com.panchospring.service.mapper;

import com.panchospring.model.dto.cuidador.CuidadorDto;
import com.panchospring.model.entity.Cuidador;
import org.springframework.stereotype.Service;

@Service
public class CuidadorMapper {
    public CuidadorDto toCuidadorDto(Cuidador cuidador) {
        return CuidadorDto.builder()
                .nombre(cuidador.getNombre())
                .idioma(cuidador.getIdioma())
                .panchoPuntos(cuidador.getPanchoPuntos())
                .puedeCuidarExotica(cuidador.isPuedeCuidarExotica())
                .build();
    }
}
