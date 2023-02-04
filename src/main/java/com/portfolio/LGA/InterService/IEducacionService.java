package com.portfolio.LGA.InterService;

import com.portfolio.LGA.dto.EducacionDto;
import com.portfolio.LGA.model.Educacion;

import java.util.List;

public interface IEducacionService {
    public List<EducacionDto> verEducacion();
    public void crearEducacion(EducacionDto educacionDto);
    public void borrarEducacion(Long id);
    public Educacion buscarEducacion(Long id);
    public Educacion editarEducacion(EducacionDto educacionDto);

}
