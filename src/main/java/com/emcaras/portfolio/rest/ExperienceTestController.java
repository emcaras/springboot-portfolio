package com.emcaras.portfolio.rest;

import com.emcaras.portfolio.dto.ExperienceDto;
import com.emcaras.portfolio.dto.ExperienceMapper;
import com.emcaras.portfolio.model.Experience;
import com.emcaras.portfolio.service.IExperienceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/experiences")
@RequiredArgsConstructor
public class ExperienceTestController {
    private final IExperienceService experienceService;
    private final ExperienceMapper experienceMapper;

    @PostMapping
    public ResponseEntity<ExperienceDto> save(@Valid @RequestBody ExperienceDto experience){
        return new ResponseEntity<>(experienceMapper.toDto(experienceService.save(experienceMapper.toEntity(experience))), HttpStatus.CREATED );
    }

    @GetMapping
    public ResponseEntity<List<ExperienceDto>> findAll(){
         return ResponseEntity.ok(experienceService.findAll().stream().map(experienceMapper::toDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExperienceDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(experienceMapper.toDto(experienceService.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "La experience con el id " + id + " no existe")
                ))
        );
    }

    @GetMapping("/personal-info/{id}")
    public ResponseEntity<List<ExperienceDto>> findByPersonalInfoId(@PathVariable Long id){
        return ResponseEntity.ok(experienceService.findByPersonalInfoId(id).stream().map(experienceMapper::toDto).toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.experienceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
