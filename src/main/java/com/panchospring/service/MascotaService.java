package com.panchospring.service;

import com.panchospring.model.dto.mascota.MascotaDto;
import com.panchospring.model.entity.Duenio;
import com.panchospring.model.entity.Mascota;
import com.panchospring.repository.DuenioRepository;
import com.panchospring.repository.MascotaRepository;
import com.panchospring.service.mapper.MascotaMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MascotaService {
    private final MascotaRepository mascotaRepository;
    private final DuenioRepository duenioRepository;
    private final MascotaMapper mapper;

    public ResponseEntity<List<MascotaDto>> getMascotas() {
        return ResponseEntity.ok(mascotaRepository.findAll().stream().map(mapper::toMascotaDto).toList());
    }

    public ResponseEntity<MascotaDto> crearMascota(MascotaDto mascota) {
        Duenio duenio = duenioRepository.findById(mascota.duenioId()).orElseThrow(() -> new EntityNotFoundException("No existe un dueño con id: " + mascota.duenioId()));
        String tipoMascota = mascota.tipoMascota();
        Mascota resultado;
        if (tipoMascota.equals("normal")) resultado = mapper.toMascota(mascota, duenio);
        else resultado = mapper.toMascotaExotica(mascota, duenio);
        mascotaRepository.save(resultado);
        return ResponseEntity.ok(mascota);
    }

    public ResponseEntity<String> eliminarMascota(int idMascota) {
        mascotaRepository.deleteById(idMascota);
        return ResponseEntity.ok("Mascota eliminada: " + idMascota);
    }

    public ResponseEntity<MascotaDto> actualizarMascota(int idMascota, MascotaDto mascotaBody) {
        Mascota mascotaBD = mascotaRepository.findById(idMascota).orElseThrow(() -> new EntityNotFoundException("No existe un mascota con id: " + idMascota));
        mascotaBD.setNombre(mascotaBody.nombre());
        mascotaBD.setLocalidad(mascotaBody.localidad());
        mascotaBD.setDescripcion(mascotaBody.descripcion());
        mascotaBD.setHoraDisponible(mascotaBody.horaDisponible());
        mascotaBD.setCodRiac(mascotaBody.codRiac());
        mascotaBD.setNumPoliza(mascotaBody.numPoliza());
        mascotaRepository.save(mascotaBD);
        return ResponseEntity.ok(mascotaBody);
    }
}
