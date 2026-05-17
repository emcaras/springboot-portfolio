package com.emcaras.portfolio.dto;

import com.emcaras.portfolio.model.Experience;
import org.springframework.stereotype.Component;

@Component
public class ExperienceMapper {
    public Experience toEntity(ExperienceDto data){
        Experience experience = new Experience();

        experience.setId(data.getId());
        experience.setJobTitle(data.getJobTitle());
        experience.setCompanyName(data.getCompanyName());
        experience.setStartDate(data.getStartDate());
        experience.setEndDate(data.getEndDate());
        experience.setDescription(data.getDescription());
        experience.setPersonalInfoId(data.getPersonalInfoId());

        return experience;
    }

    public  ExperienceDto toDto(Experience data){
        ExperienceDto experience = new ExperienceDto();

        experience.setId(data.getId());
        experience.setJobTitle(data.getJobTitle());
        experience.setCompanyName(data.getCompanyName());
        experience.setStartDate(data.getStartDate());
        experience.setEndDate(data.getEndDate());
        experience.setDescription(data.getDescription());
        experience.setPersonalInfoId(data.getPersonalInfoId());

        return experience;
    }
}
