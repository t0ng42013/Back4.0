package com.portfolio.LGA.controller;

import com.portfolio.LGA.InterService.IProyectoService;
import com.portfolio.LGA.dto.Mensaje;
import com.portfolio.LGA.dto.ProyectoDto;
import com.portfolio.LGA.model.Proyecto;
import io.micrometer.common.util.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proyecto")
@CrossOrigin(origins = {"https://lga-portfolio.web.app/","http://localhost:4200"})
public class ProyectoController {
    private final IProyectoService proyectoService;
    private final ModelMapper modelMapper;
    @Autowired
    public ProyectoController(IProyectoService proyectoService, ModelMapper modelMapper) {
        this.proyectoService = proyectoService;
        this.modelMapper = modelMapper;
    }
    @GetMapping("/lista")
    public ResponseEntity<List<ProyectoDto>> verProyecto() {
        List<ProyectoDto> proyectosDtos = proyectoService.verProyecto();
        return new ResponseEntity(proyectosDtos, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<ProyectoDto> crear(@RequestBody ProyectoDto proyectoDto) {
        if (StringUtils.isBlank(proyectoDto.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        proyectoService.crearProyecto(proyectoDto);
        return new ResponseEntity(new Mensaje("Proyecto creado"), HttpStatus.CREATED);
    }

    @PutMapping("/editar")
    public ResponseEntity<ProyectoDto> editarProyecto(@RequestBody ProyectoDto proyectoDto) {
        proyectoService.editarProyecto(proyectoDto);
        return new ResponseEntity(new Mensaje("Proyecto editado"), HttpStatus.OK);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Proyecto> buscarProyecto(@PathVariable Long id) {
        if (proyectoService.buscarProyecto(id) == null) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(proyectoService.buscarProyecto(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> borrarProyecto(@PathVariable Long id){
        proyectoService.borrarProyecto(id);
        return new ResponseEntity(new Mensaje("Proyecto borrado"),HttpStatus.NO_CONTENT);
    }
}
