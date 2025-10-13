package com.panchospring.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@SuperBuilder
public class Duenio extends Usuario {
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "duenio_cuidadores_contratados",
            joinColumns = @JoinColumn(name = "duenio_id"),
            inverseJoinColumns = @JoinColumn(name = "cuidador_contratado_id"))
    private final Set<Cuidador> cuidadoresContratados = new LinkedHashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "duenio_cuidadores_favoritos",
            joinColumns = @JoinColumn(name = "duenio_id"),
            inverseJoinColumns = @JoinColumn(name = "cuidador_favorito_id"))
    private final Set<Cuidador> cuidadoresFavoritos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "duenio", cascade = CascadeType.ALL, orphanRemoval = true)
    private final Set<Mascota> mascotas = new LinkedHashSet<>();

}
