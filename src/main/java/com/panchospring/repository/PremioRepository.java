package com.panchospring.repository;

import com.panchospring.model.entity.Premio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PremioRepository extends JpaRepository<Premio, Integer> {
}