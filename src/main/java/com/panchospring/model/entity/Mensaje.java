package com.panchospring.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Mensaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String texto;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "u_envia_id")
    private Usuario uEnvia;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "u_recibe_id")
    private Usuario uRecibe;

    @OneToMany(mappedBy = "mensaje", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Oferta> ofertas = new LinkedHashSet<>();

}
