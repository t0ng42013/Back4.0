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
public class ProyectoDto implements Serializable {
    @Schema(description="Id del proyecto", example = "1")
    private Long id;
    @Schema(description="nombre del proyecto")
    @NotBlank
    private String nombre;
    @Schema(description="descripcion del proyecto")
    private String descripcion;
    @Schema(description="url del proyecto", example = "www.example.com")
    private String imgUrl;
    @Schema(description="numero de variable del proyecto", example = "1")
    private int variableI;
}
