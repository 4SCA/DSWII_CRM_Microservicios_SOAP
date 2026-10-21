package com.example.cotizacionapi.repository;

import com.example.cotizacionapi.model.Cotizacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CotizacionRepository extends JpaRepository<Cotizacion, Long> {
}