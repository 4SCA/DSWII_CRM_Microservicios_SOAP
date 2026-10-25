package com.example.cotizacionapi.service;

import com.example.cotizacionapi.dto.ClienteDto;
import com.example.cotizacionapi.feign.ClienteFeignClient;
import com.example.cotizacionapi.kafka.event.CotizacionCreadaEvent;
import com.example.cotizacionapi.kafka.producer.CotizacionKafkaProducer;
import com.example.cotizacionapi.model.Cotizacion;
import com.example.cotizacionapi.repository.CotizacionRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CotizacionService {

    private final CotizacionRepository repository;
    private final ClienteFeignClient feign;
    private final CotizacionKafkaProducer kafkaProducer;

    public CotizacionService(CotizacionRepository repository, ClienteFeignClient feign, CotizacionKafkaProducer kafkaProducer) {
        this.repository = repository;
        this.feign = feign;
        this.kafkaProducer = kafkaProducer;
    }

    public List<Cotizacion> listar() {
        return repository.findAll();
    }

    public Cotizacion obtener(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cotización no encontrada"));
    }

    public Cotizacion guardar(Long clienteId, Cotizacion cotizacion, Long userId) {

        ClienteDto cliente = feign.obtenerCliente(clienteId);

        if (cliente == null || Boolean.FALSE.equals(cliente.getEstado())) {
            throw new RuntimeException("Cliente no válido");
        }

        cotizacion.setIdUsuario(userId);

        Cotizacion guardada = repository.save(cotizacion);

        CotizacionCreadaEvent evento = new CotizacionCreadaEvent(
                guardada.getId(),
                guardada.getIdCliente(),
                guardada.getIdUsuario(),
                guardada.getDescripcion(),
                guardada.getMontoEstimado(),
                guardada.getEstado(),
                guardada.getFecha()
        );

        kafkaProducer.publicar(evento);

        return guardada;
    }

    public Cotizacion actualizar(Long id, Cotizacion data) {

        Cotizacion c = obtener(id);

        c.setFecha(data.getFecha());
        c.setDescripcion(data.getDescripcion());
        c.setMontoEstimado(data.getMontoEstimado());
        c.setEstado(data.getEstado());
        c.setObservaciones(data.getObservaciones());

        return repository.save(c);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

}