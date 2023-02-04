package com.portfolio.LGA.controller;

import com.portfolio.LGA.InterService.IEducacionService;
import com.portfolio.LGA.dto.EducacionDto;
import com.portfolio.LGA.dto.Mensaje;
import com.portfolio.LGA.model.Educacion;
import io.micrometer.common.util.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/educacion")
@CrossOrigin(origins = {"https://lga-portfolio.web.app","http://localhost:4200"})
public class EducacionController {
    private final IEducacionService educacionService;
    private final ModelMapper modelMapper;

    @Autowired
    public EducacionController(IEducacionService educacionService, ModelMapper modelMapper) {
        this.educacionService = educacionService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/lista")
    public ResponseEntity<List<EducacionDto>> verEducacion() {
        List<EducacionDto> educacionDtos = educacionService.verEducacion();
        return new ResponseEntity(educacionDtos, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<EducacionDto> crear(@RequestBody EducacionDto educacionDto) {
        if (StringUtils.isBlank(educacionDto.getInstituto())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        educacionService.crearEducacion(educacionDto);
        return new ResponseEntity(new Mensaje("Educacion creada"), HttpStatus.CREATED);
    }

    @PutMapping("/editar")
    public ResponseEntity<EducacionDto> editarEducacion(@RequestBody EducacionDto educacionDto) {
        educacionService.editarEducacion(educacionDto);
        return new ResponseEntity(new Mensaje("Educacion editada"), HttpStatus.OK);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Educacion> buscarEducacion(@PathVariable Long id) {
        if (educacionService.buscarEducacion(id) == null) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(educacionService.buscarEducacion(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> borrarEducacion(@PathVariable Long id){
        educacionService.borrarEducacion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
