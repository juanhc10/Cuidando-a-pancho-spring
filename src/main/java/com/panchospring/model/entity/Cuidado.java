package com.panchospring.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cuidado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Embedded
    private Horario horario;
    @Column(precision = 3, scale = 2)
    private BigDecimal coste;
    private boolean pagado;
    private int panchoPuntos;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cuidador_id")
    private Cuidador cuidador;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mascota_cuidada_id")
    private Mascota mascotaCuidada;

    @OneToMany(mappedBy = "cuidado", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Notificacion> notificaciones = new LinkedHashSet<>();

    @OneToMany(mappedBy = "cuidado", orphanRemoval = true)
    private Set<Oferta> ofertas = new LinkedHashSet<>();

}
