package com.portfolio.LGA.controller;

import com.portfolio.LGA.InterService.IProyectoService;
import com.portfolio.LGA.dto.Mensaje;
import com.portfolio.LGA.dto.ProyectoDto;
import com.portfolio.LGA.model.Experiencia;
import com.portfolio.LGA.model.Proyecto;
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
@RequestMapping("/api/proyecto")
@CrossOrigin(origins = {"https://lga-portfolio.web.app/","http://localhost:4200"})
@OpenAPIDefinition(info = @Info(title = "API CRUD", description = "Operaciones CRUD para proyecto"))
public class ProyectoController {
    private final IProyectoService proyectoService;
    private final ModelMapper modelMapper;
    @Autowired
    public ProyectoController(IProyectoService proyectoService, ModelMapper modelMapper) {
        this.proyectoService = proyectoService;
        this.modelMapper = modelMapper;
    }
    @GetMapping("/lista")
    @Operation(
            summary = "Obtener todas los proyectos",
            description = "Este método devuelve una lista de todos proyecto registrados en el sistema.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de proyecto devuelta con éxito"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
    )
    public ResponseEntity<List<ProyectoDto>> verProyecto() {
        List<ProyectoDto> proyectosDtos = proyectoService.verProyecto();
        return new ResponseEntity(proyectosDtos, HttpStatus.OK);
    }

    @PostMapping("/crear")
    @Operation(
            summary = "Crear un proyecto",
            description = "Crea un proyecto con los datos especificados en el cuerpo de la solicitud")
    @ApiResponse(responseCode = "200", description = "El proyecto se ha creada correctamente")
    @ApiResponse(responseCode = "400", description = "Los datos del proyecto no son válidos")
    public ResponseEntity<ProyectoDto> crear(@RequestBody ProyectoDto proyectoDto) {
        if (StringUtils.isBlank(proyectoDto.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        proyectoService.crearProyecto(proyectoDto);
        return new ResponseEntity(new Mensaje("Proyecto creado"), HttpStatus.CREATED);
    }

    @PutMapping("/editar")
    @Operation(
            summary = "Editar un proyecto",
            description = "Edita un proyecto con los datos especificados en el cuerpo de la solicitud")
    @ApiResponse(responseCode = "200", description = "El proyecto se ha editado correctamente")
    @ApiResponse(responseCode = "400", description = "Los datos del proyecto no son válidos")
    public ResponseEntity<ProyectoDto> editarProyecto(@RequestBody ProyectoDto proyectoDto) {
        proyectoService.editarProyecto(proyectoDto);
        return new ResponseEntity(new Mensaje("Proyecto editado"), HttpStatus.OK);
    }

    @GetMapping("/buscar/{id}")
    @Operation(
            summary = "Buscar un proyecto por su ID",
            description = "Devuelve la información de un proyecto específico basada en su ID")
    @ApiResponse(responseCode = "200", description = "El proyecto fue encontrado y es devuelta en el cuerpo de la respuesta", content = @Content(schema = @Schema(implementation = Proyecto.class)))
    @ApiResponse(responseCode = "400", description = "El ID es inválido o no existe el proyecto con ese ID")
    public ResponseEntity<Proyecto> buscarProyecto(@PathVariable Long id) {
        if (proyectoService.buscarProyecto(id) == null) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(proyectoService.buscarProyecto(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "Eliminar un proyecto",
            description = "Elimina un proyecto según el ID especificado en la URL")
    public ResponseEntity<Void> borrarProyecto(@PathVariable Long id){
        proyectoService.borrarProyecto(id);
        return new ResponseEntity(new Mensaje("Proyecto borrado"),HttpStatus.NO_CONTENT);
    }
}
