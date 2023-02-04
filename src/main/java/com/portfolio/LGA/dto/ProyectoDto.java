package com.portfolio.LGA.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProyectoDto implements Serializable {
    private Long id;
    @NotBlank
    private String nombre;
    private String descripcion;
    private String imgUrl;
    private int variableI;
}
