package com.emcaras.portfolio.dto;

import com.emcaras.portfolio.model.PersonalInfo;
import org.springframework.stereotype.Component;

@Component
public class PersonalInfoMapper {
    public PersonalInfo toEntity(PersonalInfoDto data){
        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setId(data.getId());
        personalInfo.setFirstName(data.getFirstName());
        personalInfo.setLastName(data.getLastName());
        personalInfo.setTitle(data.getTitle());
        personalInfo.setProfileDescription(data.getProfileDescription());
        personalInfo.setProfileImageUrl(data.getProfileImageUrl());
        personalInfo.setYearsOfExperience(data.getYearsOfExperience());
        personalInfo.setEmail(data.getEmail());
        personalInfo.setPhone(data.getPhone());
        personalInfo.setLinkedinUrl(data.getLinkedinUrl());
        personalInfo.setGithubUrl(data.getGithubUrl());
        return personalInfo;
    }

    public PersonalInfoDto toDto(PersonalInfo data){
        PersonalInfoDto personalInfo = new PersonalInfoDto();
        personalInfo.setId(data.getId());
        personalInfo.setFirstName(data.getFirstName());
        personalInfo.setLastName(data.getLastName());
        personalInfo.setTitle(data.getTitle());
        personalInfo.setProfileDescription(data.getProfileDescription());
        personalInfo.setProfileImageUrl(data.getProfileImageUrl());
        personalInfo.setYearsOfExperience(data.getYearsOfExperience());
        personalInfo.setEmail(data.getEmail());
        personalInfo.setPhone(data.getPhone());
        personalInfo.setLinkedinUrl(data.getLinkedinUrl());
        personalInfo.setGithubUrl(data.getGithubUrl());
        return personalInfo;
    }
}
