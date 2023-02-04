package com.portfolio.LGA.service;

import com.portfolio.LGA.InterService.ISkillService;
import com.portfolio.LGA.dto.SkillDto;
import com.portfolio.LGA.model.Skill;
import com.portfolio.LGA.repository.SkillRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class SkillService implements ISkillService {
    private final SkillRepository skillRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public SkillService(SkillRepository skillRepository, ModelMapper modelMapper) {
        this.skillRepository = skillRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public List<SkillDto> verSkill() {
       List<Skill> skill =skillRepository.findAll();
       Type listType = new TypeToken<List<SkillDto>>() {}.getType();
        List<SkillDto> skillDto = modelMapper.map(skill, listType);
        return skillDto;
    }

    @Override
    public void crearSkill(SkillDto skillDto) {
        Skill skill = modelMapper.map(skillDto, Skill.class);
        skillRepository.save(skill);
    }

    @Override
    public void borrarSkill(Long id) {
        skillRepository.deleteById(id);
    }

    @Override
    public Skill buscarSkill(Long id) {
        return skillRepository.findById(id).orElse(null);
    }

    @Override
    public Skill editaSkill(SkillDto skillDto) {
    Skill skill = skillRepository.findById(skillDto.getId()).orElse(null);
    modelMapper.map(skillDto, skill);
    return skillRepository.save(skill);
    }
}
