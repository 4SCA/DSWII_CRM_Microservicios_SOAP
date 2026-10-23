package com.example.llamadaapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "llamadas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Llamada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TipoLlamada tipoLlamada;

    @Column(length = 2000)
    private String observaciones;

    @Column(nullable = false)
    private LocalDateTime fechaInicio;

    private LocalDateTime fechaFin;

    @Column(nullable = false)
    private Long idUsuario;

    @Column(nullable = false)
    private Long idCliente;

    @Column(length = 20)
    private String numeroTelefono;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoLlamada estado;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private ResultadoLlamada resultado;
}
