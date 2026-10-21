package com.example.activityapi.feign;

import com.example.activityapi.dto.ClienteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "clientes-api")
public interface ClienteFeignClient {

    @GetMapping("/clientes/{id}")
    ClienteDto obtenerCliente(@PathVariable Long id);
}