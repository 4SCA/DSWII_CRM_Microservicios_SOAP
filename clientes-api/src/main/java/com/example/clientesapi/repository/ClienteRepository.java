package com.example.clientesapi.repository;

import com.example.clientesapi.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByEstadoTrue();
    Optional<Cliente> findByIdAndEstadoTrue(Long id);
    boolean existsByRuc(String ruc);
}
