package com.portfolio.LGA.controller;

import com.portfolio.LGA.InterService.ISkillService;
import com.portfolio.LGA.dto.Mensaje;
import com.portfolio.LGA.dto.SkillDto;
import com.portfolio.LGA.model.Skill;
import io.micrometer.common.util.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skill")
@CrossOrigin(origins = {"https://lga-portfolio.web.app/","http://localhost:4200"})
public class SkillController {
    private final ISkillService skillService;
    private final ModelMapper modelMapper;
    @Autowired
    public SkillController(ISkillService skillService, ModelMapper modelMapper) {
        this.skillService = skillService;
        this.modelMapper = modelMapper;
    }
    @GetMapping("/lista")
    public ResponseEntity<List<SkillDto>> verSkill() {
        List<SkillDto> skillsDtos = skillService.verSkill();
        return new ResponseEntity(skillsDtos, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<SkillDto> crear(@RequestBody SkillDto skillDto) {
        if (StringUtils.isBlank(skillDto.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        skillService.crearSkill(skillDto);
        return new ResponseEntity(new Mensaje("Skill creado"), HttpStatus.CREATED);
    }

    @PutMapping("/editar")
    public ResponseEntity<SkillDto> editarSkill(@RequestBody SkillDto skillDto) {
        skillService.editaSkill(skillDto);
        return new ResponseEntity(new Mensaje("Skill editado"), HttpStatus.OK);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Skill> buscarSkill(@PathVariable Long id) {
        if (skillService.buscarSkill(id) == null) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(skillService.buscarSkill(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> borrarSkill(@PathVariable Long id){
        skillService.borrarSkill(id);
        return new ResponseEntity(new Mensaje("Skill borrado"), HttpStatus.OK);
    }
}
