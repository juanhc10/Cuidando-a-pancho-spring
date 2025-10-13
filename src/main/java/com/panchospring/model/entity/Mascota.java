package com.panchospring.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
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
        property = "tipoMascota"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = MascotaExotica.class, name = "exotica"),
        @JsonSubTypes.Type(value = Mascota.class, name = "normal")
})
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
    @NotBlank(message = "La localidad no puede estar vacía")
    private String localidad;
    @NotBlank(message = "La descripción no puede estar vacía")
    private String descripcion;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy'T'HH:mm:ss")
    @NotNull(message = "La hora no puede ser nula")
    private LocalDateTime horaDisponible;
    @NotBlank(message = "El codigo RIAC no puede estar vacío")
    private String codRiac;
    @NotNull
    private int numPoliza;

    @OneToMany(mappedBy = "mascotaCuidada", orphanRemoval = true)
    private Set<Cuidado> cuidados = new LinkedHashSet<>();

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "duenio_id")
    private Duenio duenio;

}
