package com.panchospring.service;

import com.panchospring.exception.MascotaFavoritaException;
import com.panchospring.exception.PuntosInsuficientesException;
import com.panchospring.model.dto.mascota.MascotaDto;
import com.panchospring.model.entity.Cuidador;
import com.panchospring.model.entity.Mascota;
import com.panchospring.model.entity.Premio;
import com.panchospring.repository.CuidadorRepository;
import com.panchospring.repository.MascotaRepository;
import com.panchospring.repository.PremioRepository;
import com.panchospring.service.mapper.MascotaMapper;
import com.panchospring.service.mapper.PremioMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    private final BCryptPasswordEncoder encoder;
    private final MascotaRepository mascotaRepository;
    private final PremioRepository premioRepository;
    private final PremioMapper premioMapper;

    public ResponseEntity<List<Cuidador>> getCuidadores() {
        return ResponseEntity.ok(cuidadorRepository.findAll());
    }

    @Transactional
    public ResponseEntity<Cuidador> habilitarCuidadoExotica(String nombre) {
        Cuidador cuidador = getCuidadorByNombre(nombre);
        cuidador.setPuedeCuidarExotica(true);
        cuidadorRepository.save(cuidador);
        return ResponseEntity.ok(cuidador);
    }

    @Transactional
    public ResponseEntity<Cuidador> actualizarCuidador(String nombre, Cuidador cuidadorBody) {
        Cuidador cuidadorBD = getCuidadorByNombre(nombre);
        cuidadorBD.setNombre(cuidadorBody.getNombre());
        if (!encoder.matches(cuidadorBody.getContrasenia(), cuidadorBD.getContrasenia()))
            cuidadorBD.setContrasenia(encoder.encode(cuidadorBody.getContrasenia()));
        cuidadorBD.setPuedeCuidarExotica(cuidadorBody.isPuedeCuidarExotica());
        cuidadorRepository.save(cuidadorBD);
        return ResponseEntity.ok(cuidadorBD);
    }

    private Cuidador getCuidadorByNombre(String nombre) {
        return cuidadorRepository.findByNombre(nombre).orElseThrow(() -> new EntityNotFoundException("No hay un cuidador con nombre: " + nombre));
    }

    private Mascota getMascotaById(int idMascota) {
        return mascotaRepository.findById(idMascota).orElseThrow(() -> new EntityNotFoundException("No hay una mascota con id: " + idMascota));
    }

    @Transactional
    public ResponseEntity<Set<MascotaDto>> aniadirMascotaFavorita(String nombreCuidador, int idMascota) {
        Cuidador cuidador = getCuidadorByNombre(nombreCuidador);
        Mascota mascota = getMascotaById(idMascota);
        Set<Mascota> mascotasFavoritas = cuidador.getMascotasFavoritas();
        if (mascotasFavoritas.add(mascota))
            return ResponseEntity.ok(mascotasFavoritas.stream().map(mascotaMapper::toMascotaDto).collect(Collectors.toSet()));
        throw new MascotaFavoritaException("La mascota favorita ya está añadida");
    }

    @Transactional
    public ResponseEntity<Set<MascotaDto>> eliminarMascotaFavorita(String nombreCuidador, int idMascota) {
        Cuidador cuidador = getCuidadorByNombre(nombreCuidador);
        Mascota mascota = getMascotaById(idMascota);
        Set<Mascota> mascotasFavoritas = cuidador.getMascotasFavoritas();
        if (mascotasFavoritas.remove(mascota))
            return ResponseEntity.ok(mascotasFavoritas.stream().map(mascotaMapper::toMascotaDto).collect(Collectors.toSet()));
        throw new MascotaFavoritaException("La mascota no está entre las favoritas");
    }


    public ResponseEntity<Set<MascotaDto>> getFavoritas(String nombreCuidador) {
        return ResponseEntity.ok(getCuidadorByNombre(nombreCuidador).getMascotasFavoritas().stream().map(mascotaMapper::toMascotaDto).collect(Collectors.toSet()));
    }

    @Transactional
    public ResponseEntity<Integer> canjearPremio(String nombreCuidador, int idPremio) {
        Cuidador cuidador = getCuidadorByNombre(nombreCuidador);
        Premio premio = premioRepository.findById(idPremio).orElseThrow(() -> new EntityNotFoundException("No existe un premio con id: " + idPremio));
        if (cuidador.getPanchoPuntos() < premio.getCoste())
            throw new PuntosInsuficientesException("Puntos insuficientes para canjear el premio");
        cuidador.setPanchoPuntos(cuidador.getPanchoPuntos() - premio.getCoste());
        premio.setCuidador(cuidador);
        premioRepository.save(premio);
        cuidadorRepository.save(cuidador);
        return ResponseEntity.ok(premio.getId());
    }
}
