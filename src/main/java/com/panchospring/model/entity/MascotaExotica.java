package com.panchospring.model.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
public class MascotaExotica extends Mascota {
    private String certificadoProcedencia;
    private String certificadoSalud;
    private String certificadoLegal;
}
