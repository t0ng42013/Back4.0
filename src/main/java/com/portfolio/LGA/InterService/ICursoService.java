package com.portfolio.LGA.InterService;

import com.portfolio.LGA.dto.CursoDto;
import com.portfolio.LGA.model.Curso;

import java.util.List;

public interface ICursoService {
    List<CursoDto> verCurso();
    public void crearCurso(CursoDto cursoDto);
    public void borrarCurso(Long id);
    public Curso buscarCurso(Long id);
    public Curso editarCurso(CursoDto cursoDto);
}
