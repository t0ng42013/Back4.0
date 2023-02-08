package com.portfolio.LGA.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CursoDto implements Serializable {
    @Schema(description="Id del curso", example = "1")
    private Long id;
    @Schema(description="nombre del curso", example = "nombre del curso")
    @NotBlank
    private String instituto;
    @Schema(description="fecha de inicio del curso", example = "01/02/2023")
    private Date inicio;
    @Schema(description="fecha de fin del curso", example = "01/02/2023")
    private Date fin;
    @Schema(description="titulo del curso")
    private String titulo;
}
