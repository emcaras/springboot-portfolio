package com.emcaras.portfolio.rest;

import com.emcaras.portfolio.model.PersonalInfo;
import com.emcaras.portfolio.service.IPersonalInfoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/test/personal-info")
@RequiredArgsConstructor
public class PersonalInfoTestController {
    private final IPersonalInfoService personalInfoService;

    @GetMapping("/all")
    public List<PersonalInfo> getAllPersonalInfo(){
        return personalInfoService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<PersonalInfo> getPersonalInfoById(@PathVariable Long id){
        Optional<PersonalInfo> personalInfo = this.personalInfoService.findById(id);
        if(personalInfo.isPresent()){
            return personalInfo;
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El id: " + id + " no existe.");
        }
    }

    @PostMapping("/save")
    public PersonalInfo savePersonalInfo(@Valid @RequestBody PersonalInfo personalInfo){
        return personalInfoService.save(personalInfo);
    }

    @DeleteMapping("/{id}")
    public void deletePersonalInfo(@PathVariable Long id){
        this.personalInfoService.delete(id);
    }
}
