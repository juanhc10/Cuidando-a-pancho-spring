package com.panchospring.model.dto.premio;

import com.panchospring.model.entity.PremioDto;
import com.panchospring.model.entity.Producto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * DTO for {@link Producto}
 */
@Getter
@AllArgsConstructor
public class ProductoDto extends PremioDto implements Serializable {
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
    @NotBlank(message = "La descripción no puede estar vacía")
    private String descripcion;
}