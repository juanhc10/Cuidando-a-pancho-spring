package com.panchospring.service;

import com.panchospring.model.dto.cuidado.CuidadoDto;
import com.panchospring.model.entity.Cuidado;
import com.panchospring.repository.CuidadoRepository;
import com.panchospring.service.mapper.CuidadoMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CuidadoService {
    private final CuidadoRepository cuidadoRepository;
    private final CuidadoMapper cuidadoMapper;

    public List<CuidadoDto> getCuidados() {
        return cuidadoRepository.findAll().stream()
                .map(cuidadoMapper::toCuidadoDto)
                .toList();
    }

    @Transactional
    public CuidadoDto crearCuidado(Cuidado cuidado) {
        Cuidado saved = cuidadoRepository.save(cuidado);
        return cuidadoMapper.toCuidadoDto(saved);
    }

    public CuidadoDto getCuidadoById(int id) {
        Cuidado cuidado = cuidadoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe un cuidado con id: " + id));
        return cuidadoMapper.toCuidadoDto(cuidado);
    }
}
