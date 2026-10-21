package com.example.activityapi.soap;

import com.example.activityapi.model.Actividad;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

import java.util.List;

@WebService(
        name = "ActividadSoap",
        targetNamespace = "http://activityapi.example.com/"
)
public interface ActividadSoap {

    @WebMethod
    @WebResult(name = "actividad")
    Actividad crear(
            @WebParam(name = "actividad") Actividad actividad
    );

    @WebMethod
    @WebResult(name = "actividad")
    Actividad obtener(
            @WebParam(name = "id") Long id
    );

    @WebMethod
    @WebResult(name = "actividad")
    List<Actividad> listar();

    @WebMethod
    @WebResult(name = "actividad")
    Actividad actualizar(
            @WebParam(name = "id") Long id,
            @WebParam(name = "actividad") Actividad actividad
    );

    @WebMethod
    void eliminar(
            @WebParam(name = "id") Long id
    );
}
