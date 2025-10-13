package com.panchospring.model.dto.cuidador;

import com.panchospring.model.entity.Cuidador;
import com.panchospring.model.entity.Idioma;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;

import java.io.Serializable;

/**
 * DTO for {@link Cuidador}
 */
@Builder
public record CuidadorDto(
        @NotBlank
        String nombre,
        @NotNull
        Idioma idioma,
        @PositiveOrZero
        int panchoPuntos,
        boolean puedeCuidarExotica
) implements Serializable {
}