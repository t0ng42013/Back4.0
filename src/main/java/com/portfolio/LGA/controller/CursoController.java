package com.portfolio.LGA.controller;

import com.portfolio.LGA.InterService.ICursoService;
import com.portfolio.LGA.dto.CursoDto;
import com.portfolio.LGA.dto.Mensaje;
import com.portfolio.LGA.model.Curso;
import com.portfolio.LGA.model.Persona;
import com.portfolio.LGA.repository.CursoRepository;
import io.micrometer.common.util.StringUtils;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/curso")
@CrossOrigin(origins = {"https://lga-portfolio.web.app","http://localhost:4200"})
@OpenAPIDefinition(info = @Info(title = "API CRUD", description = "Operaciones CRUD para cursos"))
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
    @Operation(
            summary = "Obtener todas los cursos",
            description = "Este método devuelve una lista de todos los cursos registradas en el sistema.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de cursos devuelta con éxito"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
    )
    public ResponseEntity<List<CursoDto>> verCurso() {
        List<CursoDto> cursosDtos = cursoService.verCurso();
        return new ResponseEntity(cursosDtos, HttpStatus.OK);
    }

    @PostMapping("/crear")
    @Operation(
            summary = "Crear un curso",
            description = "Crea un curso con los datos especificados en el cuerpo de la solicitud")
    @ApiResponse(responseCode = "200", description = "El curso se ha creado correctamente")
    @ApiResponse(responseCode = "400", description = "Los datos no son válidos")
    public ResponseEntity<CursoDto> crear(@RequestBody CursoDto cursoDto) {
        if (StringUtils.isBlank(cursoDto.getInstituto())) {
            return new ResponseEntity(new Mensaje("El campo es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        cursoService.crearCurso(cursoDto);
        return new ResponseEntity(new Mensaje("Curso creado"), HttpStatus.CREATED);
    }

    @PutMapping("/editar")
    @Operation(
            summary = "Editar un curso",
            description = "Edita un curso con los datos especificados en el cuerpo de la solicitud")
    @ApiResponse(responseCode = "200", description = "El curso se ha editado correctamente")
    @ApiResponse(responseCode = "400", description = "Los datos no son válidos")
    public ResponseEntity<CursoDto> editarCurso(@RequestBody CursoDto cursoDto) {
        cursoService.editarCurso(cursoDto);
        return new ResponseEntity(new Mensaje("Curso editado"), HttpStatus.OK);
    }

    @GetMapping("/buscar/{id}")
    @Operation(
            summary = "Buscar un curso por su ID",
            description = "Devuelve la información de un curso específico basada en su ID")
    @ApiResponse(responseCode = "200", description = "El curso fue encontrado y es devuelta en el cuerpo de la respuesta", content = @Content(schema = @Schema(implementation = Curso.class)))
    @ApiResponse(responseCode = "400", description = "El ID es inválido o no existe el curso con ese ID")
    public ResponseEntity<Curso> buscarCurso(@PathVariable Long id) {
        if (cursoService.buscarCurso(id) == null) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(cursoService.buscarCurso(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "Eliminar un curso",
            description = "Elimina un curso según el ID especificado en la URL")
    public ResponseEntity<Void> borrarCurso(@PathVariable Long id){
            cursoService.borrarCurso(id);
            return new ResponseEntity(new Mensaje("curso borrado"),HttpStatus.NO_CONTENT);
        }
}
