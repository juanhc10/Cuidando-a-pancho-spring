package com.panchospring.repository;

import com.panchospring.model.entity.Cuidado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuidadoRepository extends JpaRepository<Cuidado, Integer> {
}