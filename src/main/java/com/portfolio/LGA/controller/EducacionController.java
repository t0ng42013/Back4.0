package com.portfolio.LGA.controller;

import com.portfolio.LGA.InterService.IEducacionService;
import com.portfolio.LGA.dto.EducacionDto;
import com.portfolio.LGA.dto.Mensaje;
import com.portfolio.LGA.model.Educacion;
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
@RequestMapping("/api/educacion")
@CrossOrigin(origins = {"https://lga-portfolio.web.app","http://localhost:4200"})
@OpenAPIDefinition(info = @Info(title = "API CRUD", description = "Operaciones CRUD para educacion"))
public class EducacionController {
    private final IEducacionService educacionService;
    private final ModelMapper modelMapper;

    @Autowired
    public EducacionController(IEducacionService educacionService, ModelMapper modelMapper) {
        this.educacionService = educacionService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/lista")
    @Operation(
            summary = "Obtener todas las educaciones",
            description = "Este método devuelve una lista de todas las educaciones registradas en el sistema.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de educaciones devuelta con éxito"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
    )
    public ResponseEntity<List<EducacionDto>> verEducacion() {
        List<EducacionDto> educacionDtos = educacionService.verEducacion();
        return new ResponseEntity(educacionDtos, HttpStatus.OK);
    }

    @PostMapping("/crear")
    @Operation(
            summary = "Crear una educacion",
            description = "Crea una educacion con los datos especificados en el cuerpo de la solicitud")
    @ApiResponse(responseCode = "200", description = "La educacion se ha creada correctamente")
    @ApiResponse(responseCode = "400", description = "Los datos de la educacion no son válidos")
    public ResponseEntity<EducacionDto> crear(@RequestBody EducacionDto educacionDto) {
        if (StringUtils.isBlank(educacionDto.getInstituto())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        educacionService.crearEducacion(educacionDto);
        return new ResponseEntity(new Mensaje("Educacion creada"), HttpStatus.CREATED);
    }

    @PutMapping("/editar")
    @Operation(
            summary = "Editar una educacion",
            description = "Edita una educacion con los datos especificados en el cuerpo de la solicitud")
    @ApiResponse(responseCode = "200", description = "La educacion se ha editado correctamente")
    @ApiResponse(responseCode = "400", description = "Los datos de la educacion no son válidos")
    public ResponseEntity<EducacionDto> editarEducacion(@RequestBody EducacionDto educacionDto) {
        educacionService.editarEducacion(educacionDto);
        return new ResponseEntity(new Mensaje("Educacion editada"), HttpStatus.OK);
    }

    @GetMapping("/buscar/{id}")
    @Operation(
            summary = "Buscar una educacion por su ID",
            description = "Devuelve la información de una educacion específica basada en su ID")
    @ApiResponse(responseCode = "200", description = "La educacion fue encontrada y es devuelta en el cuerpo de la respuesta", content = @Content(schema = @Schema(implementation = Educacion.class)))
    @ApiResponse(responseCode = "400", description = "El ID es inválido o no existe la educacion con ese ID")
    public ResponseEntity<Educacion> buscarEducacion(@PathVariable Long id) {
        if (educacionService.buscarEducacion(id) == null) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(educacionService.buscarEducacion(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "Eliminar una educacion",
            description = "Elimina una educacion según el ID especificado en la URL")
    public ResponseEntity<Void> borrarEducacion(@PathVariable Long id){
        educacionService.borrarEducacion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
