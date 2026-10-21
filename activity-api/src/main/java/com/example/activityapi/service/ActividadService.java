package com.example.activityapi.service;

import com.example.activityapi.dto.ClienteDto;
import com.example.activityapi.feign.ClienteFeignClient;
import com.example.activityapi.model.Actividad;
import com.example.activityapi.repository.ActividadRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActividadService {

    private final ActividadRepository repository;
    private final ClienteFeignClient feign;


    public ActividadService(ActividadRepository repository,
                            ClienteFeignClient feign) {
        this.repository = repository;
        this.feign = feign;
    }

    public List<Actividad> listar() {
        return repository.findAll();
    }

    public Actividad obtener(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Actividad no encontrada"));
    }

    public Actividad guardar(Actividad actividad, Long userId) {

        ClienteDto cliente = feign.obtenerCliente(actividad.getIdCliente());

        if (cliente == null || Boolean.FALSE.equals(cliente.getEstado())) {
            throw new RuntimeException("Cliente no válido");
        }

        actividad.setIdUsuario(userId);

        return repository.save(actividad);
    }

    public Actividad actualizar(Long id, Actividad data) {

        Actividad act = obtener(id);

        act.setTipoActividad(data.getTipoActividad());
        act.setDescripcion(data.getDescripcion());
        act.setFecha(data.getFecha());
        act.setIdCliente(data.getIdCliente());

        return repository.save(act);
    }
    
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}