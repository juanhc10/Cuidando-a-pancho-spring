package com.panchospring.service;

import com.panchospring.model.dto.usuario.UsuarioLogin;
import com.panchospring.model.dto.usuario.UsuarioRegistro;
import com.panchospring.model.dto.usuario.UsuarioResponse;
import com.panchospring.model.entity.Cuidador;
import com.panchospring.model.entity.Duenio;
import com.panchospring.model.entity.Idioma;
import com.panchospring.model.entity.Usuario;
import com.panchospring.repository.CuidadorRepository;
import com.panchospring.repository.DuenioRepository;
import com.panchospring.repository.UsuarioRepository;
import com.panchospring.service.mapper.UsuarioMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final CuidadorRepository cuidadorRepository;
    private final DuenioRepository duenioRepository;
    private final UsuarioMapper mapper;
    private final BCryptPasswordEncoder encoder;

    public List<UsuarioResponse> getUsuarios() {
        return usuarioRepository.findAll().stream().map(mapper::toUsuarioResponse).toList();
    }

    public UsuarioResponse getUsuario(int idUsuario) {
        return mapper.toUsuarioResponse(usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new EntityNotFoundException("No existe un usuario con id " + idUsuario)));
    }

    @Transactional
    public UsuarioResponse registrarUsuario(UsuarioRegistro usuario) {
        String tipoUsuario = usuario.tipoUsuario();
        if ("duenio".equalsIgnoreCase(tipoUsuario)) {
            Duenio d = mapper.toDuenio(usuario);
            d.setContrasenia(encoder.encode(usuario.contrasenia()));
            Duenio saved = duenioRepository.save(d);
            return mapper.toUsuarioResponse(saved);
        }
        if ("cuidador".equalsIgnoreCase(tipoUsuario)) {
            Cuidador c = mapper.toCuidador(usuario);
            c.setContrasenia(encoder.encode(usuario.contrasenia()));
            Cuidador saved = cuidadorRepository.save(c);
            return mapper.toUsuarioResponse(saved);
        }
        throw new EntityNotFoundException("No se puede registrar un usuario que no sea ni dueño ni cuidador");
    }

    public UsuarioResponse login(UsuarioLogin usuario) {
        return usuarioRepository.findByNombre(usuario.nombre()).map(u -> {
            if (encoder.matches(usuario.contrasenia(), u.getContrasenia()))
                return mapper.toUsuarioResponse(u);
            else throw new EntityNotFoundException("Contraseña incorrecta");
        }).orElseThrow(() -> new EntityNotFoundException("Nombre de usuario incorrecto"));
    }

    @Transactional
    public void cambiarIdioma(String nombre, String idioma) {
        Usuario usuario = usuarioRepository.findByNombre(nombre)
                .orElseThrow(() -> new EntityNotFoundException("No existe un usuario con nombre " + nombre));
        usuario.setIdioma(Idioma.valueOf(idioma.toUpperCase()));
    }
}
