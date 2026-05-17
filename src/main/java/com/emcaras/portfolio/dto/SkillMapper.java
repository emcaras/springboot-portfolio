package com.emcaras.portfolio.dto;

import com.emcaras.portfolio.model.Skill;
import org.springframework.stereotype.Component;

@Component
public class SkillMapper {
    public Skill toEntity(SkillDto data){
        if(data == null) {
            return  null;
        }
        Skill skill = new Skill();
        skill.setId(data.getId());
        skill.setName(data.getName());
        skill.setLevelPercentage(data.getLevelPercentage());
        skill.setIconClass(data.getIconClass());
        skill.setPersonalInfoId(data.getPersonalInfoId());

        return skill;
    }

    public SkillDto toDto(Skill data){
        if(data == null) {
            return  null;
        }
        SkillDto dto = new SkillDto();
        dto.setId(data.getId());
        dto.setName(data.getName());
        dto.setLevelPercentage(data.getLevelPercentage());
        dto.setIconClass(data.getIconClass());
        dto.setPersonalInfoId(data.getPersonalInfoId());
        return dto;
    }
}
