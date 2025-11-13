package com.panchospring.model.dto.duenio;

import com.panchospring.model.entity.Idioma;
import lombok.Builder;

@Builder
public record DuenioDto(
        int id,
        String nombre,
        Idioma idioma
) {}
