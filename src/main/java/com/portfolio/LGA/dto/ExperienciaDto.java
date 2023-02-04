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
public class ExperienciaDto implements Serializable {
    Long id;
    @NotBlank
    private String nombre;
    private String descripcion1;
    private String descripcion2;
    private String descripcion3;
    private String descripcion4;
}
