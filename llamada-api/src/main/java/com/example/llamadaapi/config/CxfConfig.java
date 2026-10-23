package com.example.llamadaapi.config;

import com.example.llamadaapi.soap.LlamadaSoapImpl;
import jakarta.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CxfConfig {

    @Bean
    public Endpoint endpoint(Bus bus, LlamadaSoapImpl implementor) {
        EndpointImpl endpoint = new EndpointImpl(bus, implementor);
        endpoint.publish("/llamadas");
        return endpoint;
    }
}
