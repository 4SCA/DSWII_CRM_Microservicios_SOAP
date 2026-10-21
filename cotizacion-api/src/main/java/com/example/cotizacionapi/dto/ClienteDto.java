package com.example.cotizacionapi.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto {

    private Long id;
    private String rzoSocial;
    private String ruc;
    private String tipoCliente;
    private String email;
    private String telefono;
    private Boolean estado;
}