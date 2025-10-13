package com.panchospring.service.mapper;

import com.panchospring.model.dto.usuario.UsuarioRegistro;
import com.panchospring.model.dto.usuario.UsuarioResponse;
import com.panchospring.model.entity.Cuidador;
import com.panchospring.model.entity.Duenio;
import com.panchospring.model.entity.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioMapper {
    private final BCryptPasswordEncoder encoder;

    public Duenio toDuenio(UsuarioRegistro usuario) {
        return Duenio.builder().nombre(usuario.nombre()).contrasenia(encoder.encode(usuario.contrasenia())).idioma(usuario.idioma()).build();
    }

    public Cuidador toCuidador(UsuarioRegistro usuario) {
        return Cuidador.builder().nombre(usuario.nombre()).contrasenia(encoder.encode(usuario.contrasenia())).idioma(usuario.idioma()).build();
    }

    public UsuarioResponse toUsuarioResponse(Usuario usuario) {
        return new UsuarioResponse(usuario.getId(), usuario.getNombre(), usuario.getContrasenia(), usuario.getIdioma());
    }
}
