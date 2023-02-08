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
public class SkillDto implements Serializable {
    @Schema(description="Id de las skills", example = "1")
    private Long id;
    @Schema(description="nombre de las skills", example = "Spring Boot")
    @NotBlank
    private String nombre;
    @Schema(description="porcentaje de las skills", example = "80")
    @NotBlank
    private int porcentaje;
}
