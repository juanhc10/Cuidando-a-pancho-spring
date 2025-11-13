package com.panchospring.service.mapper;

import com.panchospring.model.dto.cuidado.CuidadoDto;
import com.panchospring.model.entity.Cuidado;
import org.springframework.stereotype.Service;

@Service
public class CuidadoMapper {
    public CuidadoDto toCuidadoDto(Cuidado cuidado) {
        return new CuidadoDto(
                cuidado.getId(),
                cuidado.getHorario(),
                cuidado.getCoste(),
                cuidado.isPagado(),
                cuidado.getPanchoPuntos(),
                cuidado.getCuidador() != null ? cuidado.getCuidador().getId() : 0,
                cuidado.getMascotaCuidada() != null ? cuidado.getMascotaCuidada().getId() : 0
        );
    }
}
