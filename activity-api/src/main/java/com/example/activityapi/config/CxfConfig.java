package com.example.activityapi.config;

import com.example.activityapi.soap.ActividadSoapImpl;
import jakarta.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CxfConfig {
    @Bean
    public Endpoint endpoint(Bus bus, ActividadSoapImpl implementacion) {
        EndpointImpl endpoint = new EndpointImpl(bus, implementacion);
        endpoint.publish("/actividades");
        return endpoint;
    }
}
