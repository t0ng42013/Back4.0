package com.portfolio.LGA.controller;

import com.portfolio.LGA.InterService.ICursoService;
import com.portfolio.LGA.dto.CursoDto;
import com.portfolio.LGA.dto.Mensaje;
import com.portfolio.LGA.model.Curso;
import com.portfolio.LGA.repository.CursoRepository;
import io.micrometer.common.util.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/curso")
@CrossOrigin(origins = {"https://lga-portfolio.web.app","http://localhost:4200"})
public class CursoController {
    private final ICursoService cursoService;
    private final ModelMapper modelMapper;
    private final CursoRepository cursoRepository;

    @Autowired

    public CursoController(ICursoService cursoService, ModelMapper modelMapper,
                           CursoRepository cursoRepository) {
        this.cursoService = cursoService;
        this.modelMapper = modelMapper;
        this.cursoRepository = cursoRepository;
    }

    @GetMapping("/lista")
    public ResponseEntity<List<CursoDto>> verCurso() {
        List<CursoDto> cursosDtos = cursoService.verCurso();
        return new ResponseEntity(cursosDtos, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<CursoDto> crear(@RequestBody CursoDto cursoDto) {
        if (StringUtils.isBlank(cursoDto.getInstituto())) {
            return new ResponseEntity(new Mensaje("El campo es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        cursoService.crearCurso(cursoDto);
        return new ResponseEntity(new Mensaje("Curso creado"), HttpStatus.CREATED);
    }

    @PutMapping("/editar")
    public ResponseEntity<CursoDto> editarCurso(@RequestBody CursoDto cursoDto) {
        cursoService.editarCurso(cursoDto);
        return new ResponseEntity(new Mensaje("Curso editado"), HttpStatus.OK);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Curso> buscarCurso(@PathVariable Long id) {
        if (cursoService.buscarCurso(id) == null) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(cursoService.buscarCurso(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> borrarCurso(@PathVariable Long id){
            cursoService.borrarCurso(id);
            return new ResponseEntity(new Mensaje("curso borrado"),HttpStatus.NO_CONTENT);
        }
}
