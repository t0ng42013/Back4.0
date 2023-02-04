package com.portfolio.LGA.InterService;

import com.portfolio.LGA.dto.PersonaDto;
import com.portfolio.LGA.model.Persona;

import java.util.List;

public interface IPersonaService {
    List<PersonaDto> verPersonas();
    public void crearPersona(PersonaDto personaDto);
    public void borrarPersona(Long id);
    public Persona buscarPersona(Long id);
    public Persona editarPersona(PersonaDto personaDto);
}
