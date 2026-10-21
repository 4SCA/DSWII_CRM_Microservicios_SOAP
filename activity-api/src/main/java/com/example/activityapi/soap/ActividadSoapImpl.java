package com.example.activityapi.soap;

import com.example.activityapi.model.Actividad;
import com.example.activityapi.service.ActividadService;
import jakarta.annotation.Resource;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceContext;
import jakarta.xml.ws.handler.MessageContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@WebService(
        endpointInterface =
                "com.example.activityapi.soap.ActividadSoap",
        targetNamespace = "http://activityapi.example.com/"
)
public class ActividadSoapImpl implements ActividadSoap {

    private final ActividadService service;

    @Resource
    private WebServiceContext webServiceContext;

    public ActividadSoapImpl(ActividadService service) {
        this.service = service;
    }

    @Override
    public Actividad crear(Actividad actividad) {
        if (actividad == null) {
            throw new IllegalArgumentException(
                    "La actividad es obligatoria"
            );
        }

        Long userId = obtenerUserId();

        return service.guardar(actividad, userId);
    }

    @Override
    public Actividad obtener(Long id) {
        validarId(id);
        return service.obtener(id);
    }

    @Override
    public List<Actividad> listar() {
        return service.listar();
    }

    @Override
    public Actividad actualizar(Long id, Actividad actividad) {
        validarId(id);

        if (actividad == null) {
            throw new IllegalArgumentException(
                    "La actividad es obligatoria"
            );
        }

        return service.actualizar(id, actividad);
    }

    @Override
    public void eliminar(Long id) {
        validarId(id);
        service.eliminar(id);
    }

    private void validarId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException(
                    "El id debe ser positivo"
            );
        }
    }

    private Long obtenerUserId() {
        MessageContext context =
                webServiceContext.getMessageContext();

        Object valor = context.get(
                MessageContext.HTTP_REQUEST_HEADERS
        );

        if (!(valor instanceof Map<?, ?> headers)) {
            throw new IllegalArgumentException(
                    "No se recibieron las cabeceras HTTP"
            );
        }

        Object cabecera = headers.entrySet().stream()
                .filter(entry ->
                        entry.getKey() instanceof String key
                                && key.equalsIgnoreCase("X-User-Id")
                )
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(null);

        if (!(cabecera instanceof List<?> valores)
                || valores.isEmpty()
                || valores.get(0) == null) {
            throw new IllegalArgumentException(
                    "Falta la cabecera X-User-Id"
            );
        }

        try {
            Long userId = Long.valueOf(
                    valores.get(0).toString()
            );

            if (userId <= 0) {
                throw new IllegalArgumentException(
                        "X-User-Id debe ser positivo"
                );
            }

            return userId;
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(
                    "X-User-Id no es válido"
            );
        }
    }
}
