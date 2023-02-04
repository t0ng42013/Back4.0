package com.portfolio.LGA.service;

import com.portfolio.LGA.InterService.IPersonaService;
import com.portfolio.LGA.dto.PersonaDto;
import com.portfolio.LGA.dto.PersonaNotFoundException;
import com.portfolio.LGA.model.Persona;
import com.portfolio.LGA.repository.PersonaRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PersonaService implements IPersonaService {
    private final PersonaRepository personaRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public PersonaService(PersonaRepository personaRepository, ModelMapper modelMapper) {
    this.personaRepository = personaRepository;
    this.modelMapper = modelMapper;
    }
    @Override
    public List<PersonaDto> verPersonas() {
        List<Persona> personas = personaRepository.findAll();
        Type listType = new TypeToken<List<PersonaDto>>(){}.getType();
        List<PersonaDto> personaDTOs = modelMapper.map(personas, listType);
        return personaDTOs;
    }

    @Override
    public void crearPersona(PersonaDto personaDto) {
        Persona persona = modelMapper.map(personaDto, Persona.class);
        personaRepository.save(persona);
        persona.setLastUpdated(LocalDateTime.now());
    }

    @Override
    public void borrarPersona(Long id) {
        personaRepository.deleteById(id);
    }

    @Override
    public Persona buscarPersona(Long id) {
        return personaRepository.findById(id).orElse(null);
    }

    @Override

    public Persona editarPersona(PersonaDto personaDto) {
        Persona persona = personaRepository.findById(personaDto.getId()).orElseThrow(() -> new PersonaNotFoundException(personaDto.getId()));
        modelMapper.map(personaDto, persona);
        persona.setLastUpdated(LocalDateTime.now());
        return personaRepository.save(persona);
    }
}
