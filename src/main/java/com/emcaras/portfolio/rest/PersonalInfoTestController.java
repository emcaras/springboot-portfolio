package com.emcaras.portfolio.rest;

import com.emcaras.portfolio.dto.PersonalInfoDto;
import com.emcaras.portfolio.dto.PersonalInfoMapper;
import com.emcaras.portfolio.model.PersonalInfo;
import com.emcaras.portfolio.service.IPersonalInfoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/personal-info")
@RequiredArgsConstructor
public class PersonalInfoTestController {
    private final IPersonalInfoService personalInfoService;
    private final PersonalInfoMapper personalInfoMapper;

    @GetMapping
    public ResponseEntity<List<PersonalInfoDto>> getAllPersonalInfo(){
        return ResponseEntity.ok(personalInfoService.findAll().stream().map(personalInfoMapper::toDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonalInfoDto> getPersonalInfoById(@PathVariable Long id){
        return ResponseEntity.ok(personalInfoMapper.toDto(personalInfoService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El id " + id + " no existe"))));

    }

    @PostMapping
    public ResponseEntity<PersonalInfoDto> savePersonalInfo(@Valid @RequestBody PersonalInfoDto personalInfo){
        return new ResponseEntity<>(personalInfoMapper.toDto(personalInfoService.save(personalInfoMapper.toEntity(personalInfo))), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonalInfo(@PathVariable Long id){
        this.personalInfoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
