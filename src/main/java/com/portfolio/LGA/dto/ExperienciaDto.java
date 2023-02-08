package com.portfolio.LGA.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description="Id de experiencia", example = "1")
    Long id;
    @Schema(description="nombre de experiencia")
    @NotBlank
    private String nombre;
    @Schema(description="description de experiencia")
    private String descripcion1;
    @Schema(description="description de experiencia")
    private String descripcion2;
    @Schema(description="description de experiencia")
    private String descripcion3;
    @Schema(description="description de experiencia")
    private String descripcion4;
}
