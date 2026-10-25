package com.example.cotizacionapi.kafka.event;

public record CotizacionCreadaEvent(
        Long cotizacionId,
        Long clienteId,
        Long usuarioId,
        String descripcion,
        double montoEstimado,
        String estado,
        String fecha
) {
}
