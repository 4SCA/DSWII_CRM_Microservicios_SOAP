package com.example.cotizacionapi.kafka.producer;

import com.example.cotizacionapi.kafka.event.CotizacionCreadaEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CotizacionKafkaProducer {

    private static final Logger log =
            LoggerFactory.getLogger(CotizacionKafkaProducer.class);

    private static final String TOPIC = "crm.cotizacion.creada";

    private final KafkaTemplate<String, CotizacionCreadaEvent> kafkaTemplate;

    public CotizacionKafkaProducer(
            KafkaTemplate<String, CotizacionCreadaEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publicar(CotizacionCreadaEvent evento) {
        try {
            kafkaTemplate.send(
                    TOPIC,
                    String.valueOf(evento.cotizacionId()),
                    evento
            ).whenComplete((resultado, error) -> {
                if (error != null) {
                    log.error(
                            "No se pudo publicar el evento de cotización {}",
                            evento.cotizacionId(),
                            error
                    );
                } else {
                    log.info(
                            "Evento de cotización {} publicado en Kafka",
                            evento.cotizacionId()
                    );
                }
            });
        } catch (Exception e) {
            log.error(
                    "Error iniciando la publicación del evento de cotización {}",
                    evento.cotizacionId(),
                    e
            );
        }
    }
}
