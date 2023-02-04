package com.portfolio.LGA.InterService;

import com.portfolio.LGA.dto.SkillDto;
import com.portfolio.LGA.model.Skill;

import java.util.List;

public interface ISkillService {
    public List<SkillDto> verSkill();

    public void crearSkill(SkillDto skillDto);

    public void borrarSkill(Long id);

    public Skill buscarSkill(Long id);

    public Skill editaSkill(SkillDto skillDto);
}
