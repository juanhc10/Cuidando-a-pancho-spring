package com.panchospring.repository;

import com.panchospring.model.entity.Cuidador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CuidadorRepository extends JpaRepository<Cuidador, Integer> {
    Optional<Cuidador> findByNombre(String nombre);
}