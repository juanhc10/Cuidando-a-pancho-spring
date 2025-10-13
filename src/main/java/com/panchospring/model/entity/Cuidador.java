package com.panchospring.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
public class Cuidador extends Usuario {
    @ManyToMany(mappedBy = "cuidadoresContratados", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private final Set<Duenio> empleadores = new LinkedHashSet<>();
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "cuidador_mascotas_favoritas", joinColumns = @JoinColumn(name = "cuidador_id"), inverseJoinColumns = @JoinColumn(name = "mascota_id"))
    private final Set<Mascota> mascotasFavoritas = new LinkedHashSet<>();
    @OneToMany(mappedBy = "cuidador", cascade = CascadeType.ALL, orphanRemoval = true)
    private final Set<Cuidado> cuidados = new LinkedHashSet<>();
    @OneToMany(mappedBy = "cuidador", cascade = CascadeType.ALL, orphanRemoval = true)
    private final Set<Premio> premios = new LinkedHashSet<>();

    private int panchoPuntos;
    private boolean puedeCuidarExotica;
}