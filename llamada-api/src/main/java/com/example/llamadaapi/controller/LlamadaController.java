package com.example.llamadaapi.controller;

import com.example.llamadaapi.model.Llamada;
import com.example.llamadaapi.service.LlamadaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actividades")
public class LlamadaController {

    private final LlamadaService service;

    public LlamadaController(LlamadaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Llamada> listar() {
        return service.listar();
    }
    @GetMapping("/{id}")
    public Llamada obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PostMapping
    public Llamada guardar(@RequestBody Llamada actividad,
                           @RequestHeader("X-User-Id") Long userId) {
        return service.guardar(actividad, userId);
    }

    @PutMapping("/{id}")
    public Llamada actualizar(@PathVariable Long id,
                              @RequestBody Llamada actividad) {
        return service.actualizar(id, actividad);
    }
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}