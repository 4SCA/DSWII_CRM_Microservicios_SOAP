package com.example.authservice.dto;

import com.example.authservice.model.RolEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String nombres;
    private String apellidos;
    private String correo;
    private String clave;
    private RolEnum rol;
}
