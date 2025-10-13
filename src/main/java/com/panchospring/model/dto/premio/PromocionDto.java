package com.panchospring.model.dto.premio;

import com.panchospring.model.entity.Horario;
import com.panchospring.model.entity.PremioDto;
import com.panchospring.model.entity.Promocion;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link Promocion}
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PromocionDto extends PremioDto implements Serializable {
    @NotNull
    private Horario horario;
}