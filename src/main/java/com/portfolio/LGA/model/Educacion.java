package com.portfolio.LGA.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Educacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50, message = "no cumple la longitud")
    private String instituto;
    @Temporal(TemporalType.DATE)
    private Date inicio;
    @Temporal(TemporalType.DATE)
    private Date fin;
    @Size(max = 50, message = "no cumple la longitud")
    private String titulo;


    public Educacion(String instituto, Date inicio, Date fin, String titulo) {
        this.instituto = instituto;
        this.inicio = inicio;
        this.fin = fin;
        this.titulo = titulo;
    }

}
