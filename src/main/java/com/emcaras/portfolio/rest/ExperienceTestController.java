package com.emcaras.portfolio.rest;

import com.emcaras.portfolio.model.Experience;
import com.emcaras.portfolio.service.IExperienceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/test/experience")
@RequiredArgsConstructor
public class ExperienceTestController {
    private final IExperienceService experienceService;

    @PostMapping
    public Experience save(@Valid @RequestBody Experience experience){
        return this.experienceService.save(experience);
    }

    @GetMapping("/all")
    public List<Experience> findAll(){
         return this.experienceService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Experience> findById(@PathVariable Long id){
        return Optional.of(this.experienceService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "el id " + id + " no existe.")));
    }

    @GetMapping("/personal-info/{id}")
    public List<Experience> findByPersonalInfoId(@PathVariable Long id){
        return this.experienceService.findByPersonalInfoId(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        this.experienceService.delete(id);
    }
}
