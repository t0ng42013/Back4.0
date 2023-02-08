package com.portfolio.LGA.controller;

import com.portfolio.LGA.InterService.IPersonaService;
import com.portfolio.LGA.dto.Mensaje;
import com.portfolio.LGA.dto.PersonaDto;
import com.portfolio.LGA.model.Persona;
import io.micrometer.common.util.StringUtils;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persona")
@CrossOrigin(origins = {"https://lga-portfolio.web.app","http://localhost:4200"})
@OpenAPIDefinition(info = @Info(title = "API CRUD de personas", description = "Operaciones CRUD para personas"))
public class PersonaController {
    private final IPersonaService personaService;
    private final ModelMapper modelMapper;
    @Autowired
    private PersonaController(IPersonaService personaService, ModelMapper modelMapper) {
        this.personaService = personaService;
        this.modelMapper = modelMapper;
    };

    @GetMapping("/lista")
    @Operation(
            summary = "Obtener todas las personas",
            description = "Este método devuelve una lista de todas las personas registradas en el sistema.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de personas devuelta con éxito"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
    )
    @ResponseBody
    public ResponseEntity<List<PersonaDto>> verPersonas() {
        List<PersonaDto> personasDtos = personaService.verPersonas();
        return new ResponseEntity(personasDtos, HttpStatus.OK);
    }

    @PostMapping("/crear")
    @Operation(
            summary = "Crear una persona",
            description = "Crea una persona con los datos especificados en el cuerpo de la solicitud")
    @ApiResponse(responseCode = "200", description = "La persona se ha creada correctamente")
    @ApiResponse(responseCode = "400", description = "Los datos de la persona no son válidos")
    public ResponseEntity<PersonaDto> crearPersona(@RequestBody PersonaDto personaDto) {

            if (StringUtils.isBlank(personaDto.getNombre())) {
                return new ResponseEntity(new Mensaje("El campo es obligatorio"), HttpStatus.BAD_REQUEST);
            }
            personaService.crearPersona(personaDto);
            return new ResponseEntity(new Mensaje("Persona Creada"), HttpStatus.CREATED);

    }

    @PutMapping("/editar")
    @Operation(
            summary = "Editar una persona",
            description = "Edita una persona con los datos especificados en el cuerpo de la solicitud")
    @ApiResponse(responseCode = "200", description = "La persona se ha editado correctamente")
    @ApiResponse(responseCode = "400", description = "Los datos de la persona no son válidos")
    public ResponseEntity<PersonaDto> editarPersona(@RequestBody PersonaDto personaDto) {

            personaService.editarPersona(personaDto);
            return new ResponseEntity(new Mensaje("Persona editada"), HttpStatus.OK);
    }

    @GetMapping("/buscar/{id}")
    @Operation(
            summary = "Buscar una persona por su ID",
            description = "Devuelve la información de una persona específica basada en su ID")
    @ApiResponse(responseCode = "200", description = "La persona fue encontrada y es devuelta en el cuerpo de la respuesta", content = @Content(schema = @Schema(implementation = Persona.class)))
    @ApiResponse(responseCode = "400", description = "El ID es inválido o no existe la persona con ese ID")
    public ResponseEntity<Persona> buscarPersona(@PathVariable Long id) {

            if (personaService.buscarPersona(id) == null) {
                return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity(personaService.buscarPersona(id), HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "Eliminar una persona",
            description = "Elimina una persona según el ID especificado en la URL")
    public ResponseEntity<Void> borrarPersona(@Parameter(description = "ID de la persona a eliminar", required = true) @PathVariable Long id){
        personaService.borrarPersona(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
