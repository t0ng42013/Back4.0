package com.portfolio.LGA.controller;

import com.portfolio.LGA.InterService.ISkillService;
import com.portfolio.LGA.dto.Mensaje;
import com.portfolio.LGA.dto.SkillDto;
import com.portfolio.LGA.model.Experiencia;
import com.portfolio.LGA.model.Skill;
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
@RequestMapping("/api/skill")
@CrossOrigin(origins = {"https://lga-portfolio.web.app/","http://localhost:4200"})
@OpenAPIDefinition(info = @Info(title = "API CRUD", description = "Operaciones CRUD para skill"))
public class SkillController {
    private final ISkillService skillService;
    private final ModelMapper modelMapper;
    @Autowired
    public SkillController(ISkillService skillService, ModelMapper modelMapper) {
        this.skillService = skillService;
        this.modelMapper = modelMapper;
    }
    @GetMapping("/lista")
    @Operation(
            summary = "Obtener todas las skills",
            description = "Este método devuelve una lista de todas las skills registradas en el sistema.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de skills devuelta con éxito"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
    )
    public ResponseEntity<List<SkillDto>> verSkill() {
        List<SkillDto> skillsDtos = skillService.verSkill();
        return new ResponseEntity(skillsDtos, HttpStatus.OK);
    }

    @PostMapping("/crear")
    @Operation(
            summary = "Crear una skill",
            description = "Crea una skill con los datos especificados en el cuerpo de la solicitud")
    @ApiResponse(responseCode = "200", description = "La skill se ha creada correctamente")
    @ApiResponse(responseCode = "400", description = "Los datos de la skill no son válidos")
    public ResponseEntity<SkillDto> crear(@RequestBody SkillDto skillDto) {
        if (StringUtils.isBlank(skillDto.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        skillService.crearSkill(skillDto);
        return new ResponseEntity(new Mensaje("Skill creado"), HttpStatus.CREATED);
    }

    @PutMapping("/editar")
    @Operation(
            summary = "Editar una skill",
            description = "Edita una skill con los datos especificados en el cuerpo de la solicitud")
    @ApiResponse(responseCode = "200", description = "La skill se ha editado correctamente")
    @ApiResponse(responseCode = "400", description = "Los datos de la skill no son válidos")
    public ResponseEntity<SkillDto> editarSkill(@RequestBody SkillDto skillDto) {
        skillService.editaSkill(skillDto);
        return new ResponseEntity(new Mensaje("Skill editado"), HttpStatus.OK);
    }

    @GetMapping("/buscar/{id}")
    @Operation(
            summary = "Buscar una skill por su ID",
            description = "Devuelve la información de una skill específica basada en su ID")
    @ApiResponse(responseCode = "200", description = "La skill fue encontrada y es devuelta en el cuerpo de la respuesta", content = @Content(schema = @Schema(implementation = Skill.class)))
    @ApiResponse(responseCode = "400", description = "El ID es inválido o no existe la skill con ese ID")
    public ResponseEntity<Skill> buscarSkill(@PathVariable Long id) {
        if (skillService.buscarSkill(id) == null) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(skillService.buscarSkill(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "Eliminar una skill",
            description = "Elimina una skill según el ID especificado en la URL")
    public ResponseEntity<Void> borrarSkill(@PathVariable Long id){
        skillService.borrarSkill(id);
        return new ResponseEntity(new Mensaje("Skill borrado"), HttpStatus.OK);
    }
}
