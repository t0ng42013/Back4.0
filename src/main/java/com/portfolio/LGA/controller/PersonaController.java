package com.portfolio.LGA.controller;

import com.portfolio.LGA.InterService.IPersonaService;
import com.portfolio.LGA.dto.Mensaje;
import com.portfolio.LGA.dto.PersonaDto;
import com.portfolio.LGA.model.Persona;
import io.micrometer.common.util.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persona")
@CrossOrigin(origins = {"https://lga-portfolio.web.app","http://localhost:4200"})
public class PersonaController {
    private final IPersonaService personaService;
    private final ModelMapper modelMapper;
    @Autowired
    private PersonaController(IPersonaService personaService, ModelMapper modelMapper) {
        this.personaService = personaService;
        this.modelMapper = modelMapper;
    };

    @GetMapping("/lista")
    @ResponseBody
    public ResponseEntity<List<PersonaDto>> verPersonas() {
        List<PersonaDto> personasDtos = personaService.verPersonas();
        return new ResponseEntity(personasDtos, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<PersonaDto> crearPersona(@RequestBody PersonaDto personaDto) {
        if (StringUtils.isBlank(personaDto.getNombre())) {
            return new ResponseEntity(new Mensaje("El campo es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        personaService.crearPersona(personaDto);
        return new ResponseEntity(new Mensaje("Persona Creada"), HttpStatus.CREATED);
    }

    @PutMapping("/editar")
    public ResponseEntity<PersonaDto> editarPersona(@RequestBody PersonaDto personaDto) {
        personaService.editarPersona(personaDto);
       return new ResponseEntity(new Mensaje("Persona editada"), HttpStatus.OK);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Persona> buscarPersona(@PathVariable Long id) {
        if (personaService.buscarPersona(id) == null) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(personaService.buscarPersona(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> borrarPersona(@PathVariable Long id){
        personaService.borrarPersona(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
