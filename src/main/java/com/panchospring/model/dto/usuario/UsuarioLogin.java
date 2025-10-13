package com.panchospring.model.dto.usuario;

import com.panchospring.model.entity.Usuario;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * DTO for {@link Usuario}
 */
public record UsuarioLogin(
        @NotBlank(message = "El nombre no puede estar vacío")
        String nombre,
        @NotBlank(message = "La contraseña no puede estar vacía")
        String contrasenia
) implements Serializable {
}