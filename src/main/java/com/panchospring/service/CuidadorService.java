package com.panchospring.service;

import com.panchospring.exception.MascotaFavoritaException;
import com.panchospring.exception.PuntosInsuficientesException;
import com.panchospring.model.dto.cuidador.CuidadorDto;
import com.panchospring.model.dto.mascota.MascotaDto;
import com.panchospring.model.entity.Cuidador;
import com.panchospring.model.entity.Mascota;
import com.panchospring.model.entity.Premio;
import com.panchospring.repository.CuidadorRepository;
import com.panchospring.repository.MascotaRepository;
import com.panchospring.repository.PremioRepository;
import com.panchospring.service.mapper.CuidadorMapper;
import com.panchospring.service.mapper.MascotaMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CuidadorService {
    private final CuidadorRepository cuidadorRepository;
    private final MascotaMapper mascotaMapper;
    private final CuidadorMapper cuidadorMapper;
    private final BCryptPasswordEncoder encoder;
    private final MascotaRepository mascotaRepository;
    private final PremioRepository premioRepository;

    public List<CuidadorDto> getCuidadores() {
        return cuidadorRepository.findAll().stream()
                .map(cuidadorMapper::toCuidadorDto)
                .toList();
    }

    @Transactional
    public CuidadorDto habilitarCuidadoExotica(String nombre) {
        Cuidador cuidador = getCuidadorByNombre(nombre);
        cuidador.setPuedeCuidarExotica(true);
        cuidadorRepository.save(cuidador);
        return cuidadorMapper.toCuidadorDto(cuidador);
    }

    @Transactional
    public CuidadorDto actualizarCuidador(String nombre, CuidadorDto cuidadorBody) {
        Cuidador cuidadorBD = getCuidadorByNombre(nombre);
        cuidadorBD.setNombre(cuidadorBody.nombre());
        cuidadorBD.setIdioma(cuidadorBody.idioma());
        cuidadorBD.setPuedeCuidarExotica(cuidadorBody.puedeCuidarExotica());
        cuidadorRepository.save(cuidadorBD);
        return cuidadorMapper.toCuidadorDto(cuidadorBD);
    }

    private Cuidador getCuidadorByNombre(String nombre) {
        return cuidadorRepository.findByNombre(nombre)
                .orElseThrow(() -> new EntityNotFoundException("No hay un cuidador con nombre: " + nombre));
    }

    private Mascota getMascotaById(int idMascota) {
        return mascotaRepository.findById(idMascota)
                .orElseThrow(() -> new EntityNotFoundException("No hay una mascota con id: " + idMascota));
    }

    @Transactional
    public Set<MascotaDto> aniadirMascotaFavorita(String nombreCuidador, int idMascota) {
        Cuidador cuidador = getCuidadorByNombre(nombreCuidador);
        Mascota mascota = getMascotaById(idMascota);
        Set<Mascota> mascotasFavoritas = cuidador.getMascotasFavoritas();
        if (mascotasFavoritas.add(mascota)) {
            cuidadorRepository.save(cuidador);
            return mascotasFavoritas.stream()
                    .map(mascotaMapper::toMascotaDto)
                    .collect(Collectors.toSet());
        }
        throw new MascotaFavoritaException("La mascota favorita ya está añadida");
    }

    @Transactional
    public Set<MascotaDto> eliminarMascotaFavorita(String nombreCuidador, int idMascota) {
        Cuidador cuidador = getCuidadorByNombre(nombreCuidador);
        Mascota mascota = getMascotaById(idMascota);
        Set<Mascota> mascotasFavoritas = cuidador.getMascotasFavoritas();
        if (mascotasFavoritas.remove(mascota)) {
            cuidadorRepository.save(cuidador);
            return mascotasFavoritas.stream()
                    .map(mascotaMapper::toMascotaDto)
                    .collect(Collectors.toSet());
        }
        throw new MascotaFavoritaException("La mascota no está entre las favoritas");
    }

    public Set<MascotaDto> getFavoritas(String nombreCuidador) {
        return getCuidadorByNombre(nombreCuidador).getMascotasFavoritas().stream()
                .map(mascotaMapper::toMascotaDto)
                .collect(Collectors.toSet());
    }

    @Transactional
    public int canjearPremio(String nombreCuidador, int idPremio) {
        Cuidador cuidador = getCuidadorByNombre(nombreCuidador);
        Premio premio = premioRepository.findById(idPremio)
                .orElseThrow(() -> new EntityNotFoundException("No existe un premio con id: " + idPremio));
        if (cuidador.getPanchoPuntos() < premio.getCoste())
            throw new PuntosInsuficientesException("Puntos insuficientes para canjear el premio");
        cuidador.setPanchoPuntos(cuidador.getPanchoPuntos() - premio.getCoste());
        premio.setCuidador(cuidador);
        premioRepository.save(premio);
        cuidadorRepository.save(cuidador);
        return premio.getId();
    }
}
