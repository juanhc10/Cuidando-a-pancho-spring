package com.panchospring.model.dto.usuario;

import com.panchospring.model.entity.Idioma;
import com.panchospring.model.entity.Usuario;

import java.io.Serializable;

/**
 * DTO for {@link Usuario}
 */
public record UsuarioResponse(
        int id,
        String nombre,
        String contrasenia,
        Idioma idioma
) implements Serializable {
}