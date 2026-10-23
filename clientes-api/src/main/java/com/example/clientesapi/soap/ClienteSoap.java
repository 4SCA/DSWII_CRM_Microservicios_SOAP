package com.example.clientesapi.soap;

import com.example.clientesapi.model.Cliente;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

import java.util.List;

@WebService(
        name = "ClienteSoap",
        targetNamespace = "http://clientesapi.example.com/"
)
public interface ClienteSoap {

    @WebMethod(operationName = "crearCliente")
    @WebResult(name = "cliente")
    Cliente crear(
            @WebParam(name = "cliente") Cliente cliente
    );

    @WebMethod(operationName = "obtenerCliente")
    @WebResult(name = "cliente")
    Cliente obtener(
            @WebParam(name = "id") Long id
    );

    @WebMethod(operationName = "listarClientes")
    @WebResult(name = "cliente")
    List<Cliente> listar();

    @WebMethod(operationName = "actualizarCliente")
    @WebResult(name = "cliente")
    Cliente actualizar(
            @WebParam(name = "id") Long id,
            @WebParam(name = "cliente") Cliente cliente
    );

    @WebMethod(operationName = "eliminarCliente")
    @WebResult(name = "resultado")
    void eliminar(
            @WebParam(name = "id") Long id
    );

    @WebMethod(operationName = "listarClientesActivos")
    @WebResult(name = "cliente")
    List<Cliente> listarActivos();

    @WebMethod(operationName = "restaurarCliente")
    @WebResult(name = "cliente")
    Cliente restaurar(
            @WebParam(name = "id") Long id
    );
}