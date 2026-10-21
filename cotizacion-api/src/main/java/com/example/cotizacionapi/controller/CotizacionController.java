package com.example.cotizacionapi.controller;

import com.example.cotizacionapi.model.Cotizacion;
import com.example.cotizacionapi.service.CotizacionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cotizaciones")
public class CotizacionController {

    private final CotizacionService service;

    public CotizacionController(CotizacionService service) {
        this.service = service;
    }

    @GetMapping
    public List<Cotizacion> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Cotizacion obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PostMapping("/{clienteId}")
    public Cotizacion guardar(@PathVariable Long clienteId,
                              @RequestBody Cotizacion cotizacion,
                              @RequestHeader("X-User-Id") Long userId) {

        return service.guardar(clienteId, cotizacion, userId);
    }

    @PutMapping("/{id}")
    public Cotizacion actualizar(@PathVariable Long id,
                                 @RequestBody Cotizacion cotizacion) {
        return service.actualizar(id, cotizacion);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
