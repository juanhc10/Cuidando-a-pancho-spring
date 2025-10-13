package com.panchospring.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Oferta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private EstadoOferta estadoOferta;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cuidado_id")
    private Cuidado cuidado;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mensaje_id")
    private Mensaje mensaje;

}
