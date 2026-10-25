package com.example.notificacionservice.kafka;

import com.example.notificacionservice.event.CotizacionCreadaEvent;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CotizacionCreadaConsumer {

    private static final Logger log =
            LoggerFactory.getLogger(CotizacionCreadaConsumer.class);

    @PostConstruct
    public void iniciar() {
        log.info(">>> CotizacionCreadaConsumer se ha inicializado");
    }

    @KafkaListener(
            topics = "crm.cotizacion.creada",
            groupId = "notification-service-group"
    )
    public void recibir(CotizacionCreadaEvent evento) {
        log.info(">>> EVENTO KAFKA RECIBIDO");
        log.info("Cotización: {}", evento.cotizacionId());
        log.info("Cliente: {}", evento.clienteId());
        log.info("Monto: {}", evento.montoEstimado());
    }
}