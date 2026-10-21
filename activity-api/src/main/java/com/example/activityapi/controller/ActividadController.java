package com.example.activityapi.controller;

import com.example.activityapi.model.Actividad;
import com.example.activityapi.service.ActividadService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actividades")
public class ActividadController {

    private final ActividadService service;

    public ActividadController(ActividadService service) {
        this.service = service;
    }

    @GetMapping
    public List<Actividad> listar() {
        return service.listar();
    }
    @GetMapping("/{id}")
    public Actividad obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PostMapping
    public Actividad guardar(@RequestBody Actividad actividad,
                             @RequestHeader("X-User-Id") Long userId) {
        return service.guardar(actividad, userId);
    }

    @PutMapping("/{id}")
    public Actividad actualizar(@PathVariable Long id,
                                @RequestBody Actividad actividad) {
        return service.actualizar(id, actividad);
    }
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}