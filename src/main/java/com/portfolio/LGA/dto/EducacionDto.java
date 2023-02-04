package com.portfolio.LGA.dto;

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

public class EducacionDto implements Serializable {
    private Long id;
    @NotBlank
    private String instituto;
    private Date inicio;
    private Date fin;
    private String titulo;
}
