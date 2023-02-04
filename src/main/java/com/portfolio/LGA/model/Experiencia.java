package com.portfolio.LGA.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Experiencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50, message = "no cumple la longitud")
    private String nombre;
    @Lob
    @Size(max = 5000, message = "no cumple la longitud")
    private String descripcion1;
    @Lob
    @Size(max = 5000, message = "no cumple la longitud")
    private String descripcion2;
    @Lob
    @Size(max = 5000, message = "no cumple la longitud")
    private String descripcion3;
    @Lob
    @Size(max = 5000, message = "no cumple la longitud")
    private String descripcion4;

    public Experiencia(String nombre, String descripcion1, String descripcion2, String descripcion3, String descripcion4) {
        this.nombre = nombre;
        this.descripcion1 = descripcion1;
        this.descripcion2 = descripcion2;
        this.descripcion3 = descripcion3;
        this.descripcion4 = descripcion4;
    }
}
