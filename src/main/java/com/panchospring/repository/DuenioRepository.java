package com.panchospring.repository;

import com.panchospring.model.entity.Duenio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DuenioRepository extends JpaRepository<Duenio, Integer> {
    Optional<Duenio> findByNombre(String nombre);

}