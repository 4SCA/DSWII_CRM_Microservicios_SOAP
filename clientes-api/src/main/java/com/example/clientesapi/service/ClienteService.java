package com.example.clientesapi.service;

import com.example.clientesapi.model.Cliente;
import com.example.clientesapi.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listAll() {
        return clienteRepository.findAll();
    }

    public List<Cliente> findByEstadoTrue() {
        return clienteRepository.findByEstadoTrue();
    }

    public Cliente findById(Long id) {
        return clienteRepository.findById(id).orElseThrow(()->new RuntimeException("Cliente no encontrado"));
    }

    public Cliente findByIdAndEstadoTrue(Long id) {
        return clienteRepository.findByIdAndEstadoTrue(id).orElseThrow(()-> new RuntimeException("Cliente no encontrado"));
    }

    public Cliente save(Cliente cliente) {
        if (clienteRepository.existsByRuc(cliente.getRuc())) {
            throw new RuntimeException("Ya existe un cliente con ese RUC");
        }

        cliente.setEstado(true);

        return clienteRepository.save(cliente);
    }

    public Cliente update(Long id, Cliente updCliente) {
        Cliente cliente = findByIdAndEstadoTrue(id);

        cliente.setRzoSocial(updCliente.getRzoSocial());
        cliente.setRuc(updCliente.getRuc());
        cliente.setTipoCliente(updCliente.getTipoCliente());
        cliente.setEmail(updCliente.getEmail());
        cliente.setTelefono(updCliente.getTelefono());

        return clienteRepository.save(cliente);
    }

    public void softDeleteById(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        cliente.setEstado(false);
        clienteRepository.save(cliente);
    }

    public Cliente restaurar(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        cliente.setEstado(true);

        return clienteRepository.save(cliente);
    }
}
