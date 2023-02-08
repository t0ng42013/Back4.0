package com.portfolio.LGA.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BannerDto implements Serializable {
    @Schema(description="Id del banner", example = "1")
    private Long id;
    @Schema(description="Url del banner", example = "www.example.com")
    private String nombreUrl;
}
