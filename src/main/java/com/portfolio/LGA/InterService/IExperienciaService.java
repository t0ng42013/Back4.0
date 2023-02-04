package com.portfolio.LGA.InterService;

import com.portfolio.LGA.dto.ExperienciaDto;
import com.portfolio.LGA.model.Experiencia;

import java.util.List;

public interface IExperienciaService {
    List<ExperienciaDto> verExperiencia();
    public void crearExperiencia(ExperienciaDto experienciaDto);
    public void borrarExperiencia(Long id);
    public Experiencia buscarExperiencia(Long id);
    public Experiencia editarExperiencia(ExperienciaDto experienciaDto);
}

