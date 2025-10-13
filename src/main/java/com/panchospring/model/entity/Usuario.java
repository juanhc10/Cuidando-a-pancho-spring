package com.panchospring.model.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
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
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "tipoUsuario"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Duenio.class, name = "duenio"),
        @JsonSubTypes.Type(value = Cuidador.class, name = "cuidador")
})
public abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    @Column(unique = true, nullable = false)
    protected String nombre;
    @Column(nullable = false)
    protected String contrasenia;
    @Enumerated(EnumType.STRING)
    protected Idioma idioma;

    @OneToMany(mappedBy = "uRecibe", cascade = CascadeType.ALL, orphanRemoval = true)
    private final Set<Mensaje> msjRecibidos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "uEnvia", cascade = CascadeType.ALL, orphanRemoval = true)
    private final Set<Mensaje> msjEnviados = new LinkedHashSet<>();
}