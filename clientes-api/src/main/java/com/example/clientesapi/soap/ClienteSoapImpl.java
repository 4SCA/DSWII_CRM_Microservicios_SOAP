
package com.example.clientesapi.soap;

import com.example.clientesapi.model.Cliente;
import com.example.clientesapi.service.ClienteService;
import jakarta.jws.WebService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@WebService(
        endpointInterface =
                "com.example.clientesapi.soap.ClienteSoap",
        targetNamespace = "http://clientesapi.example.com/"
)
public class ClienteSoapImpl implements ClienteSoap {

    private final ClienteService clienteService;

    public ClienteSoapImpl(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Override
    public Cliente crear(Cliente cliente) {
        return clienteService.save(cliente);
    }

    @Override
    public Cliente obtener(Long id) {
        return clienteService.findById(id);
    }

    @Override
    public List<Cliente> listar() {
        return clienteService.listAll();
    }

    @Override
    public Cliente actualizar(Long id, Cliente cliente) {
        return clienteService.update(id, cliente);
    }

    @Override
    public void eliminar(Long id) {
        clienteService.softDeleteById(id);
    }

    @Override
    public List<Cliente> listarActivos() {
        return clienteService.findByEstadoTrue();
    }

    @Override
    public Cliente restaurar(Long id) {
        return clienteService.restaurar(id);
    }
}

