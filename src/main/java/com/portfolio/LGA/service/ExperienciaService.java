package com.portfolio.LGA.service;

import com.portfolio.LGA.InterService.IExperienciaService;
import com.portfolio.LGA.dto.ExperienciaDto;
import com.portfolio.LGA.dto.PersonaNotFoundException;
import com.portfolio.LGA.model.Experiencia;
import com.portfolio.LGA.repository.ExperienciaRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class ExperienciaService implements IExperienciaService {
    private final ExperienciaRepository experienciaRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public ExperienciaService(ExperienciaRepository experienciaRepository, ModelMapper modelMapper) {
        this.experienciaRepository = experienciaRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public List<ExperienciaDto> verExperiencia() {
        List<Experiencia> experiencias = experienciaRepository.findAll();
        Type listType = new TypeToken<List<ExperienciaDto>>(){}.getType();
        List<ExperienciaDto> experienciaDTOs = modelMapper.map(experiencias, listType);
        return experienciaDTOs;
    }

    @Override
    public void crearExperiencia(ExperienciaDto experienciaDto) {
        Experiencia experiencia = modelMapper.map(experienciaDto, Experiencia.class);
        experienciaRepository.save(experiencia);
    }

    @Override
    public void borrarExperiencia(Long id) {
        experienciaRepository.deleteById(id);
    }

    @Override
    public Experiencia buscarExperiencia(Long id) {
        return experienciaRepository.findById(id).orElse(null);
    }

    @Override
    public Experiencia editarExperiencia(ExperienciaDto experienciaDto) {
        Experiencia experiencia = experienciaRepository.findById(experienciaDto.getId()).orElseThrow(() -> new PersonaNotFoundException(experienciaDto.getId()));
        modelMapper.map(experienciaDto, experiencia);
        return experienciaRepository.save(experiencia);
    }
}
