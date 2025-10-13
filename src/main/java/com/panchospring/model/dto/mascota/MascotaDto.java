package com.panchospring.model.dto.mascota;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.panchospring.model.entity.Mascota;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link Mascota}
 */
public record MascotaDto(
        @NotBlank(message = "El nombre no puede estar vacío")
        String nombre,
        @NotBlank(message = "La localidad no puede estar vacía")
        String localidad,
        @NotBlank(message = "La descripción no puede estar vacía")
        String descripcion,
        @NotNull(message = "La hora no puede ser nula")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy'T'HH:mm:ss")
        LocalDateTime horaDisponible,
        @NotBlank(message = "El codigo RIAC no puede estar vacío")
        String codRiac,
        @NotNull
        int numPoliza,
        @NotNull
        @Positive
        int duenioId,
        @NotBlank(message = "El tipo de mascota no puede estar vacío")
        @Pattern(regexp = "normal|exotica", message = "El tipo de usuario debe ser 'normal' o 'exotica'")
        String tipoMascota
) implements Serializable {
}