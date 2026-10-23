package com.example.llamadaapi.repository;

import com.example.llamadaapi.model.Llamada;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LlamadaRepository extends JpaRepository<Llamada, Long> {
}