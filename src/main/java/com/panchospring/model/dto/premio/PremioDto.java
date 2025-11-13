package com.panchospring.model.dto.premio;

import com.panchospring.model.entity.Premio;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link Premio}
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PremioDto implements Serializable {
    @Positive(message = "El coste tiene que ser positivo")
    protected int coste;
}