package com.panchospring.service.mapper;

import com.panchospring.model.dto.duenio.DuenioDto;
import com.panchospring.model.entity.Duenio;
import org.springframework.stereotype.Service;

@Service
public class DuenioMapper {
    public DuenioDto toDuenioDto(Duenio duenio) {
        return DuenioDto.builder()
                .id(duenio.getId())
                .nombre(duenio.getNombre())
                .idioma(duenio.getIdioma())
                .build();
    }
}
