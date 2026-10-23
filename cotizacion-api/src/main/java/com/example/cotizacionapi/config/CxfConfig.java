package com.example.cotizacionapi.config;

import com.example.cotizacionapi.soap.CotizacionSoapImpl;
import jakarta.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CxfConfig {
    @Bean
    public Endpoint endpoint(Bus bus, CotizacionSoapImpl implementor) {
        EndpointImpl endpoint = new EndpointImpl(bus, implementor);
        endpoint.publish("/cotizaciones");
        return endpoint;
    }
}
