package com.example.llamadaapi.soap;

import com.example.llamadaapi.model.Llamada;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

import java.util.List;

@WebService(
        name = "LlamadaSoap",
        targetNamespace = "http://activityapi.example.com/"
)
public interface LlamadaSoap {

    @WebMethod(operationName = "crearLlamada")
    @WebResult(name = "llamada")
    Llamada crear(
            @WebParam(name = "llamada") Llamada llamada
    );

    @WebMethod(operationName = "obtenerLlamada")
    @WebResult(name = "llamada")
    Llamada obtener(
            @WebParam(name = "id") Long id
    );

    @WebMethod(operationName = "listarLlamadas")
    @WebResult(name = "llamada")
    List<Llamada> listar();

    @WebMethod(operationName = "actualizarLlamada")
    @WebResult(name = "llamada")
    Llamada actualizar(
            @WebParam(name = "id") Long id,
            @WebParam(name = "llamada") Llamada llamada
    );

    @WebMethod(operationName = "eliminarLlamada")
    @WebResult(name = "resultado")
    void eliminar(
            @WebParam(name = "id") Long id
    );
}
