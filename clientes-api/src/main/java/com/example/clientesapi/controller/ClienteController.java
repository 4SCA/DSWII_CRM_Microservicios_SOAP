package com.example.clientesapi.controller;

import com.example.clientesapi.model.Cliente;
import com.example.clientesapi.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> listar() {
        return clienteService.listAll();
    }

    @GetMapping("/{id}")
    public Cliente obtenerPorId(@PathVariable Long id) {
        return clienteService.findById(id);
    }

    @GetMapping("/activos")
    public List<Cliente> listarActivos(){
        return clienteService.findByEstadoTrue();
    }


    @GetMapping("/activos/{id}")
    public Cliente obtenerPorIdActivo(@PathVariable Long id) {
        return clienteService.findByIdAndEstadoTrue(id);
    }

    @PostMapping
    public Cliente guardar(@RequestBody Cliente cliente) {
        return clienteService.save(cliente);
    }

    @PutMapping("/{id}")
    public Cliente actualizar(@PathVariable Long id,
                              @RequestBody Cliente cliente) {
        return clienteService.update(id, cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        clienteService.softDeleteById(id);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/{id}/restaurar")
    public Cliente restaurar(@PathVariable Long id) {
        return clienteService.restaurar(id);
    }
}
