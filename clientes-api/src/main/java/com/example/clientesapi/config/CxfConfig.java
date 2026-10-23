package com.example.clientesapi.config;

import com.example.clientesapi.soap.ClienteSoapImpl;
import jakarta.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CxfConfig {
    @Bean
    public Endpoint endpoint(Bus bus, ClienteSoapImpl implementor) {
        EndpointImpl endpoint = new EndpointImpl(bus, implementor);
        endpoint.publish("/clientes");
        return endpoint;
    }
}
