package com.portfolio.LGA.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50, message = "no cumple la longitud")
    private String nombre;

    @Lob
    @Size(max = 5000, message = "no cumple la longitud")
    private String descripcion;

    @Size(max = 100, message = "no cumple la longitud")
    private String imgUrl;
    @Min(0)
    @Max(15)
    private int variableI;

    public Proyecto(String nombre, String descripcion, String imgUrl, int variableI) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imgUrl = imgUrl;
        this.variableI = variableI;
    }
}
