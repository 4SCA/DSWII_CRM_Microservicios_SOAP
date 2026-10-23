package com.example.llamadaapi.service;

import com.example.llamadaapi.dto.ClienteDto;
import com.example.llamadaapi.feign.ClienteFeignClient;
import com.example.llamadaapi.model.Llamada;
import com.example.llamadaapi.repository.LlamadaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LlamadaService {

    private final LlamadaRepository repository;
    private final ClienteFeignClient feign;

    public LlamadaService(
            LlamadaRepository repository,
            ClienteFeignClient feign) {
        this.repository = repository;
        this.feign = feign;
    }

    @Transactional(readOnly = true)
    public List<Llamada> listar() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Llamada obtener(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Llamada no encontrada con ID: " + id));
    }

    public Llamada guardar(Llamada llamada, Long userId) {

        ClienteDto cliente = feign.obtenerCliente(llamada.getIdCliente());
        if (cliente == null || !Boolean.TRUE.equals(cliente.getEstado())) {
            throw new RuntimeException("Cliente no válido o inactivo");
        }
        llamada.setIdUsuario(userId);
        return repository.save(llamada);
    }

    public Llamada actualizar(Long id, Llamada data) {

        Llamada llamada = obtener(id);

        if (data.getIdCliente() != null
                && !data.getIdCliente().equals(llamada.getIdCliente())) {

            ClienteDto cliente = feign.obtenerCliente(data.getIdCliente());

            if (cliente == null || !Boolean.TRUE.equals(cliente.getEstado())) {
                throw new RuntimeException("Cliente no válido o inactivo");
            }

            llamada.setIdCliente(data.getIdCliente());
        }
        llamada.setTipoLlamada(data.getTipoLlamada());
        llamada.setObservaciones(data.getObservaciones());
        llamada.setFechaInicio(data.getFechaInicio());
        llamada.setFechaFin(data.getFechaFin());
        llamada.setNumeroTelefono(data.getNumeroTelefono());
        llamada.setEstado(data.getEstado());
        llamada.setResultado(data.getResultado());
        return repository.save(llamada);
    }

    public void eliminar(Long id) {
        Llamada llamada = obtener(id);
        repository.delete(llamada);
    }
}