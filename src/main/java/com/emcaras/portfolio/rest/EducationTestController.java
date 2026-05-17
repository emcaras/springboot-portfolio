package com.emcaras.portfolio.rest;

import com.emcaras.portfolio.dto.EducationDto;
import com.emcaras.portfolio.dto.EducationMapper;
import com.emcaras.portfolio.model.Education;
import com.emcaras.portfolio.service.IEducationService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/educations")
@RequiredArgsConstructor
public class EducationTestController {
    private final IEducationService educationService;
    private final EducationMapper educationMapper;

    @PostMapping
    @Operation(summary = "Guardar una educacion")
    public ResponseEntity<EducationDto> save(@Valid @RequestBody EducationDto education){
        return new ResponseEntity<>(educationMapper.toDto(educationService.save(educationMapper.toEntity(education))), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EducationDto>> findAll(){
        return ResponseEntity.ok(educationService.findAll().stream().map(educationMapper::toDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EducationDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(educationMapper.toDto(educationService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))));
    }

    @GetMapping("/personal-info/{id}")
    ResponseEntity<List<EducationDto>> findByPersonalInfoId(@PathVariable Long id){
        return ResponseEntity.ok(educationService.findAllByPersonalInfoId(id).stream().map(educationMapper::toDto).toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.educationService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
