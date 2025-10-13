package com.panchospring.service;

import com.panchospring.exception.CuidadorFavoritoException;
import com.panchospring.model.dto.cuidador.CuidadorDto;
import com.panchospring.model.entity.Cuidador;
import com.panchospring.model.entity.Duenio;
import com.panchospring.repository.CuidadorRepository;
import com.panchospring.repository.DuenioRepository;
import com.panchospring.service.mapper.CuidadorMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DuenioService {
    private final DuenioRepository duenioRepository;
    private final CuidadorMapper mapper;
    private final CuidadorRepository cuidadorRepository;

    public List<Duenio> getDuenios() {
        return duenioRepository.findAll();
    }

    private Duenio getDuenioByNombre(String nombre) {
        return duenioRepository.findByNombre(nombre).orElseThrow(() -> new EntityNotFoundException("No hay un Dueño con nombre: " + nombre));
    }


    private Cuidador getCuidadorById(int idCuidador) {
        return cuidadorRepository.findById(idCuidador).orElseThrow(() -> new EntityNotFoundException("No hay un Cuidador con id: " + idCuidador));
    }

    @Transactional
    public ResponseEntity<Set<CuidadorDto>> aniadirCuidadorFavorita(String nombreDuenio, int idCuidador) {
        Duenio duenio = getDuenioByNombre(nombreDuenio);
        Cuidador cuidador = getCuidadorById(idCuidador);
        Set<Cuidador> cuidadoresFavoritos = duenio.getCuidadoresFavoritos();
        if (cuidadoresFavoritos.add(cuidador))
            return ResponseEntity.ok(cuidadoresFavoritos.stream().map(mapper::toCuidadorDto).collect(Collectors.toSet()));
        throw new CuidadorFavoritoException("El cuidador favorito ya está añadido");
    }

    @Transactional
    public ResponseEntity<Set<CuidadorDto>> eliminarCuidadorFavorita(String nombreDuenio, int idCuidador) {
        Duenio duenio = getDuenioByNombre(nombreDuenio);
        Cuidador cuidador = getCuidadorById(idCuidador);
        Set<Cuidador> cuidadoresFavoritos = duenio.getCuidadoresFavoritos();
        if (cuidadoresFavoritos.remove(cuidador))
            return ResponseEntity.ok(cuidadoresFavoritos.stream().map(mapper::toCuidadorDto).collect(Collectors.toSet()));
        throw new CuidadorFavoritoException("El cuidador favorito no está entre los favoritos");
    }

    public ResponseEntity<Set<CuidadorDto>> getFavoritas(String nombreDuenio) {
        return ResponseEntity.ok(getDuenioByNombre(nombreDuenio).getCuidadoresFavoritos().stream().map(mapper::toCuidadorDto).collect(Collectors.toSet()));
    }
}
