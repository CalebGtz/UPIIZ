package com.example.restaurant.repositories;

import com.example.restaurant.entities.Platillo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatilloRepository extends JpaRepository<Platillo, Long> { }
