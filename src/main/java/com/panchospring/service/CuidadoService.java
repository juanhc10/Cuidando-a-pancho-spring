package com.panchospring.service;

import com.panchospring.model.entity.Cuidado;
import com.panchospring.repository.CuidadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CuidadoService {
    private final CuidadoRepository cuidadoRepository;

    public ResponseEntity<List<Cuidado>> getCuidados() {
        return ResponseEntity.ok(cuidadoRepository.findAll());
    }
}
