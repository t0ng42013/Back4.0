package com.portfolio.LGA.service;

import com.portfolio.LGA.InterService.IProyectoService;
import com.portfolio.LGA.dto.PersonaDto;
import com.portfolio.LGA.dto.ProyectoDto;
import com.portfolio.LGA.model.Proyecto;
import com.portfolio.LGA.repository.ProyectoRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class ProyectoService implements IProyectoService {
    private final ProyectoRepository proyectoRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public ProyectoService(ProyectoRepository proyectoRepository, ModelMapper modelMapper) {
        this.proyectoRepository = proyectoRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public List<ProyectoDto> verProyecto() {
        List<Proyecto> proyectos = proyectoRepository.findAll();
        Type listType = new TypeToken<List<PersonaDto>>(){}.getType();
        List<ProyectoDto> proyectoDtos = modelMapper.map(proyectos, listType);
        return proyectoDtos;
    }

    @Override
    public void crearProyecto(ProyectoDto proyectoDto) {
        Proyecto proyecto = modelMapper.map(proyectoDto, Proyecto.class);
        proyectoRepository.save(proyecto);
    }

    @Override
    public void borrarProyecto(Long id) {
        proyectoRepository.deleteById(id);
    }

    @Override
    public Proyecto buscarProyecto(Long id) {
        return proyectoRepository.findById(id).orElse(null);
    }

    @Override
    public Proyecto editarProyecto(ProyectoDto proyectoDto) {
       Proyecto proyectos = proyectoRepository.findById(proyectoDto.getId()).orElse(null);
       modelMapper.map(proyectoDto, proyectos);
        return proyectoRepository.save(proyectos);
    }
}
