package com.panchospring.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "tipoPremio"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Producto.class, name = "producto"),
        @JsonSubTypes.Type(value = Promocion.class, name = "promocion")
})
public abstract class Premio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Positive(message = "El coste tiene que ser positivo")
    @Column(nullable = false)
    private int coste;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy'T'HH:mm:ss")
    private LocalDateTime fechaCompra;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "cuidador_id")
    private Cuidador cuidador;

    @PreUpdate
    public void setFechaCompra() {
        this.fechaCompra = LocalDateTime.now();
    }
}
