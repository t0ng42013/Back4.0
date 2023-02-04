package com.portfolio.LGA.service;

import com.portfolio.LGA.InterService.IEducacionService;
import com.portfolio.LGA.dto.EducacionDto;
import com.portfolio.LGA.model.Educacion;
import com.portfolio.LGA.repository.EducacionRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class EducacionService implements IEducacionService {
    private final EducacionRepository educacionRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public EducacionService(EducacionRepository educacionRepository, ModelMapper modelMapper) {
        this.educacionRepository = educacionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<EducacionDto> verEducacion() {
        List<Educacion> educacions = educacionRepository.findAll();
        Type listType = new TypeToken<List<EducacionDto>>(){}.getType();
        List<EducacionDto> educacionDtos = modelMapper.map(educacions, listType);
        return educacionDtos;
    }

    @Override
    public void crearEducacion(EducacionDto educacionDto) {
        Educacion educacion = modelMapper.map(educacionDto, Educacion.class);
        educacionRepository.save(educacion);
    }

    @Override
    public void borrarEducacion(Long id) {
        educacionRepository.deleteById(id);
    }

    @Override
    public Educacion buscarEducacion(Long id) {
        return educacionRepository.findById(id).orElse(null);
    }

    @Override
    public Educacion editarEducacion(EducacionDto educacionDto) {
        Educacion educacion = educacionRepository.findById(educacionDto.getId()).orElse(null);
        modelMapper.map(educacionDto, educacion);
        return educacionRepository.save(educacion);
    }
}
