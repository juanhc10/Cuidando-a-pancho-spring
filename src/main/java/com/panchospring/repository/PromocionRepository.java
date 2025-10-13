package com.panchospring.repository;

import com.panchospring.model.entity.Promocion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromocionRepository extends JpaRepository<Promocion, Integer> {
}