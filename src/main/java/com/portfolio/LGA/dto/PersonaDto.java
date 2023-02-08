package com.portfolio.LGA.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description="Representa a una persona")
public class PersonaDto implements Serializable {
    @Schema(description="ID de la persona", example = "1")
    private Long id;
    @Schema(description="Nombre de la persona", example = "John ")
    @NotBlank
    private String nombre;
    @Schema(description="Apellido de la persona", example = "Doe")
    @NotBlank
    private String apellido;
    @Schema(description="Domicilio de la persona")
    private String domicilio;
    @Schema(description="Titulo de la persona")
    private String titulo;
    @Schema(description="SobreMi de la persona")
    private String sobreMi;
    @Schema(description="Url de la persona")
    private  String url;
    @Schema(description="LastUpdate de la persona")
    private LocalDateTime lastUpdated;
}
