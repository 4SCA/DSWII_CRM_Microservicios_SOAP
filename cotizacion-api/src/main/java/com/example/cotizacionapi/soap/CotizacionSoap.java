package com.example.cotizacionapi.soap;

import com.example.cotizacionapi.model.Cotizacion;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

import java.util.List;

@WebService(
        name = "CotizacionSoap",
        targetNamespace = "http://cotizacionapi.example.com/"
)
public interface CotizacionSoap {

    @WebMethod(operationName = "crearCotizacion")
    @WebResult(name = "cotizacion")
    Cotizacion crear(
            @WebParam(name = "clienteId") Long clienteId,
            @WebParam(name = "cotizacion") Cotizacion cotizacion,
            @WebParam(name = "userId") Long userId
    );

    @WebMethod(operationName = "obtenerCotizacion")
    @WebResult(name = "cotizacion")
    Cotizacion obtener(
            @WebParam(name = "id") Long id
    );

    @WebMethod(operationName = "listarCotizaciones")
    @WebResult(name = "cotizacion")
    List<Cotizacion> listar();

    @WebMethod(operationName = "actualizarCotizacion")
    @WebResult(name = "cotizacion")
    Cotizacion actualizar(
            @WebParam(name = "id") Long id,
            @WebParam(name = "cotizacion") Cotizacion cotizacion
    );

    @WebMethod(operationName = "eliminarCotizacion")
    @WebResult(name = "resultado")
    void eliminar(
            @WebParam(name = "id") Long id
    );
}
