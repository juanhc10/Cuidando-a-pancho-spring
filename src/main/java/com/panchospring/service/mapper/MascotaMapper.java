package com.panchospring.service.mapper;

import com.panchospring.model.dto.mascota.MascotaDto;
import com.panchospring.model.entity.Duenio;
import com.panchospring.model.entity.Mascota;
import com.panchospring.model.entity.MascotaExotica;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MascotaMapper {
    public Mascota toMascota(MascotaDto mascota, Duenio duenio) {
        return Mascota.builder()
                .nombre(mascota.nombre())
                .codRiac(mascota.codRiac())
                .descripcion(mascota.descripcion())
                .numPoliza(mascota.numPoliza())
                .localidad(mascota.localidad())
                .horaDisponible(mascota.horaDisponible())
                .duenio(duenio)
                .build();
    }

    public MascotaExotica toMascotaExotica(MascotaDto mascota, Duenio duenio) {
        return MascotaExotica.builder()
                .nombre(mascota.nombre())
                .codRiac(mascota.codRiac())
                .descripcion(mascota.descripcion())
                .numPoliza(mascota.numPoliza())
                .localidad(mascota.localidad())
                .horaDisponible(mascota.horaDisponible())
                .duenio(duenio)
                .build();
    }

    public MascotaDto toMascotaDto(Mascota mascota) {
        return new MascotaDto(mascota.getNombre(),
                mascota.getLocalidad(),
                mascota.getDescripcion(),
                mascota.getHoraDisponible(),
                mascota.getCodRiac(),
                mascota.getNumPoliza(),
                mascota.getDuenio().getId(),
                "normal");
    }

}
