package com.portfolio.LGA.service;

import com.portfolio.LGA.InterService.ICursoService;
import com.portfolio.LGA.dto.CursoDto;
import com.portfolio.LGA.model.Curso;
import com.portfolio.LGA.repository.CursoRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class CursoService implements ICursoService {
    private final CursoRepository cursoRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CursoService(CursoRepository cursoRepository, ModelMapper modelMapper) {
        this.cursoRepository = cursoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CursoDto> verCurso() {
        List<Curso> cursos = cursoRepository.findAll();
        Type listType = new TypeToken<List<CursoDto>>() {
        }.getType();
        List<CursoDto> cursoDtos = modelMapper.map(cursos, listType);
        return cursoDtos;
    }

    @Override
    public void crearCurso(CursoDto cursoDto) {
        Curso curso = modelMapper.map(cursoDto, Curso.class);
        cursoRepository.save(curso);
    }

    @Override
    public void borrarCurso(Long id) {
        cursoRepository.deleteById(id);
    }

    @Override
    public Curso buscarCurso(Long id) {
        return cursoRepository.findById(id).orElse(null);
    }

    @Override
    public Curso editarCurso(CursoDto cursoDto) {
        Curso cursos = cursoRepository.findById(cursoDto.getId()).orElse(null);
       modelMapper.map(cursoDto,cursos);
        return cursoRepository.save(cursos);
    }
}
