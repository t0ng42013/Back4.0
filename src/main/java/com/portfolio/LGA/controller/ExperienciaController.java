package com.portfolio.LGA.controller;

import com.portfolio.LGA.InterService.IExperienciaService;
import com.portfolio.LGA.dto.ExperienciaDto;
import com.portfolio.LGA.dto.Mensaje;
import com.portfolio.LGA.model.Experiencia;
import com.portfolio.LGA.model.Persona;
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
@RequestMapping("/api/experiencia")
@CrossOrigin(origins = {"https://lga-portfolio.web.app","http://localhost:4200"})
@OpenAPIDefinition(info = @Info(title = "API CRUD", description = "Operaciones CRUD para experiencia"))
public class ExperienciaController {
    private final IExperienciaService experienciaService;
    private final ModelMapper modelMapper;

    @Autowired
    private ExperienciaController(IExperienciaService experienciaService, ModelMapper modelMapper) {
        this.experienciaService = experienciaService;
        this.modelMapper = modelMapper;
    };

    @GetMapping("/lista")
    @Operation(
            summary = "Obtener todas las experiencia",
            description = "Este método devuelve una lista de todas las experiencias registradas en el sistema.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de experiencias devuelta con éxito"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
    )
    @ResponseBody
    public ResponseEntity<List<ExperienciaDto>> verExperiencia() {
        List<ExperienciaDto> experienciaDtos = experienciaService.verExperiencia();
        return new ResponseEntity(experienciaDtos, HttpStatus.OK);
    }

    @PostMapping("/crear")
    @Operation(
            summary = "Crear una experiencia",
            description = "Crea una experiencia con los datos especificados en el cuerpo de la solicitud")
    @ApiResponse(responseCode = "200", description = "La experiencia se ha creada correctamente")
    @ApiResponse(responseCode = "400", description = "Los datos de la experiencia no son válidos")
    public ResponseEntity<ExperienciaDto> crearExperiencia(@RequestBody ExperienciaDto experienciaDto) {
        if (StringUtils.isBlank(experienciaDto.getNombre())) {
            return new ResponseEntity(new Mensaje("El campo es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        experienciaService.crearExperiencia(experienciaDto);
        return new ResponseEntity(new Mensaje("Experiencia Creada"), HttpStatus.CREATED);
    }

    @PutMapping("/editar")
    @Operation(
            summary = "Editar una experiencia",
            description = "Edita una experiencia con los datos especificados en el cuerpo de la solicitud")
    @ApiResponse(responseCode = "200", description = "La experiencia se ha editado correctamente")
    @ApiResponse(responseCode = "400", description = "Los datos de la experiencia no son válidos")
    public ResponseEntity<ExperienciaDto> editarExperiencia(@RequestBody ExperienciaDto experienciaDto) {
        experienciaService.editarExperiencia(experienciaDto);
        return new ResponseEntity(new Mensaje("Experiencia editada"), HttpStatus.OK);
    }

    @GetMapping("/buscar/{id}")
    @Operation(
            summary = "Buscar una experiencia por su ID",
            description = "Devuelve la información de una experiencia específica basada en su ID")
    @ApiResponse(responseCode = "200", description = "La experiencia fue encontrada y es devuelta en el cuerpo de la respuesta", content = @Content(schema = @Schema(implementation = Experiencia.class)))
    @ApiResponse(responseCode = "400", description = "El ID es inválido o no existe la experiencia con ese ID")
    public ResponseEntity<Experiencia> buscarExperiencia(@PathVariable Long id) {
        if (experienciaService.buscarExperiencia(id) == null) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(experienciaService.buscarExperiencia(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "Eliminar una experiencia",
            description = "Elimina una experiencia según el ID especificado en la URL")
    public ResponseEntity<Void> borrarExperiencia(@PathVariable Long id) {
        experienciaService.borrarExperiencia(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}