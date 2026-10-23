package com.example.cotizacionapi.soap;

import com.example.cotizacionapi.model.Cotizacion;
import com.example.cotizacionapi.service.CotizacionService;
import jakarta.jws.WebService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@WebService(
        endpointInterface =
                "com.example.cotizacionapi.soap.CotizacionSoap",
        targetNamespace = "http://cotizacionapi.example.com/"
)
public class CotizacionSoapImpl implements CotizacionSoap {

    private final CotizacionService cotizacionService;

    public CotizacionSoapImpl(CotizacionService cotizacionService) {
        this.cotizacionService = cotizacionService;
    }

    @Override
    public Cotizacion crear(
            Long clienteId,
            Cotizacion cotizacion,
            Long userId) {

        // Guardar el ID del cliente en la cotización.
        cotizacion.setIdCliente(clienteId);

        return cotizacionService.guardar(
                clienteId,
                cotizacion,
                userId
        );
    }

    @Override
    public Cotizacion obtener(Long id) {
        return cotizacionService.obtener(id);
    }

    @Override
    public List<Cotizacion> listar() {
        return cotizacionService.listar();
    }

    @Override
    public Cotizacion actualizar(
            Long id,
            Cotizacion cotizacion) {

        return cotizacionService.actualizar(id, cotizacion);
    }

    @Override
    public void eliminar(Long id) {
        cotizacionService.eliminar(id);
    }
}
