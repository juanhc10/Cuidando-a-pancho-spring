package com.panchospring.model.dto.usuario;

import com.panchospring.annotation.ValidEnum;
import com.panchospring.model.entity.Idioma;
import com.panchospring.model.entity.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

/**
 * DTO for {@link Usuario}
 */
public record UsuarioRegistro(
        @NotBlank(message = "El nombre no puede estar vacío")
        String nombre,
        @NotBlank(message = "La contraseña no puede estar vacía")
        String contrasenia,
        @NotNull(message = "El idioma no puede estar vacío")
        @ValidEnum(enumClass = Idioma.class, message = "El idioma debe ser un valor válido")
        Idioma idioma,
        @NotBlank(message = "El tipo de usuario no puede estar vacío")
        @Pattern(regexp = "duenio|cuidador", message = "El tipo de usuario debe ser 'duenio' o 'cuidador'")
        String tipoUsuario
) implements Serializable {
}