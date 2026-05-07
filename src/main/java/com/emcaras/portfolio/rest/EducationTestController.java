package com.emcaras.portfolio.rest;

import com.emcaras.portfolio.model.Education;
import com.emcaras.portfolio.service.IEducationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/test/education")
@RequiredArgsConstructor
public class EducationTestController {
    private final IEducationService educationService;

    @PostMapping
    @Description("Guardar o actualizar una educacion en la base de datos.")
    public Education save(@Valid @RequestBody Education education){
        return this.educationService.save(education);
    }

    @GetMapping("/all")
    public List<Education> findAll(){
        return this.educationService.findAll();
    }

    @GetMapping("/id/{id}")
    public Optional<Education> findById(@PathVariable Long id){
        return Optional.of(this.educationService.findById(id).stream().findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El id " + id + " no existe"))
        );
    }

    @GetMapping("/personal-info/{id}")
    List<Education> findByPersonalInfoId(@PathVariable Long id){
        return this.educationService.findAllByPersonalInfoId(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        this.educationService.delete(id);
    }


}
