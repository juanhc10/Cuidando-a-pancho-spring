package com.panchospring.service;

import com.panchospring.exception.CuidadorFavoritoException;
import com.panchospring.model.dto.cuidador.CuidadorDto;
import com.panchospring.model.dto.duenio.DuenioDto;
import com.panchospring.model.entity.Cuidador;
import com.panchospring.model.entity.Duenio;
import com.panchospring.repository.CuidadorRepository;
import com.panchospring.repository.DuenioRepository;
import com.panchospring.service.mapper.CuidadorMapper;
import com.panchospring.service.mapper.DuenioMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DuenioService {
    private final DuenioRepository duenioRepository;
    private final CuidadorMapper cuidadorMapper;
    private final DuenioMapper duenioMapper;
    private final CuidadorRepository cuidadorRepository;

    public List<DuenioDto> getDuenios() {
        return duenioRepository.findAll().stream()
                .map(duenioMapper::toDuenioDto)
                .toList();
    }

    private Duenio getDuenioByNombre(String nombre) {
        return duenioRepository.findByNombre(nombre)
                .orElseThrow(() -> new EntityNotFoundException("No hay un Dueño con nombre: " + nombre));
    }

    private Cuidador getCuidadorById(int idCuidador) {
        return cuidadorRepository.findById(idCuidador)
                .orElseThrow(() -> new EntityNotFoundException("No hay un Cuidador con id: " + idCuidador));
    }

    @Transactional
    public Set<CuidadorDto> aniadirCuidadorFavorita(String nombreDuenio, int idCuidador) {
        Duenio duenio = getDuenioByNombre(nombreDuenio);
        Cuidador cuidador = getCuidadorById(idCuidador);
        Set<Cuidador> cuidadoresFavoritos = duenio.getCuidadoresFavoritos();
        if (cuidadoresFavoritos.add(cuidador)) {
            duenioRepository.save(duenio);
            return cuidadoresFavoritos.stream()
                    .map(cuidadorMapper::toCuidadorDto)
                    .collect(Collectors.toSet());
        }
        throw new CuidadorFavoritoException("El cuidador favorito ya está añadido");
    }

    @Transactional
    public Set<CuidadorDto> eliminarCuidadorFavorita(String nombreDuenio, int idCuidador) {
        Duenio duenio = getDuenioByNombre(nombreDuenio);
        Cuidador cuidador = getCuidadorById(idCuidador);
        Set<Cuidador> cuidadoresFavoritos = duenio.getCuidadoresFavoritos();
        if (cuidadoresFavoritos.remove(cuidador)) {
            duenioRepository.save(duenio);
            return cuidadoresFavoritos.stream()
                    .map(cuidadorMapper::toCuidadorDto)
                    .collect(Collectors.toSet());
        }
        throw new CuidadorFavoritoException("El cuidador favorito no está entre los favoritos");
    }

    public Set<CuidadorDto> getFavoritas(String nombreDuenio) {
        return getDuenioByNombre(nombreDuenio).getCuidadoresFavoritos().stream()
                .map(cuidadorMapper::toCuidadorDto)
                .collect(Collectors.toSet());
    }
}
