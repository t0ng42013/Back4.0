package com.portfolio.LGA.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    @Size(max = 100, message = "no cumple la longitud")
    private String nombreUrl;

    public Banner(String nombreUrl) {
        this.nombreUrl = nombreUrl;
    }
}
