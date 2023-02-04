package com.portfolio.LGA.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonaDto implements Serializable {
    private Long id;
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    private String domicilio;

    private String titulo;

    private String sobreMi;

    private  String url;
    private LocalDateTime lastUpdated;
}
