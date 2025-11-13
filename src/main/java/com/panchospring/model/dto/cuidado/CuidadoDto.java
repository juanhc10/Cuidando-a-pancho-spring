package com.panchospring.model.dto.cuidado;

import com.panchospring.model.entity.Horario;
import java.math.BigDecimal;

public record CuidadoDto(
        int id,
        Horario horario,
        BigDecimal coste,
        boolean pagado,
        int panchoPuntos,
        int cuidadorId,
        int mascotaCuidadaId
) {}
