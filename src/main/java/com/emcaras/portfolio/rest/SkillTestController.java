package com.emcaras.portfolio.rest;

import com.emcaras.portfolio.model.Skill;
import com.emcaras.portfolio.service.ISkillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/test/skill")
@RequiredArgsConstructor
public class SkillTestController {
    private final ISkillService skillService;

    @PostMapping("/save")
    public Skill saveSkill(@Valid @RequestBody Skill skill){
        return this.skillService.save(skill);
    }

    @GetMapping("/all")
    public List<Skill> getAllSkills(){
        return this.skillService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Skill> getSkillById(@PathVariable Long id){
        return Optional.of(this.skillService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Skill no encontrada")));
    }

    @GetMapping("/personal-info/{id}")
    public List<Skill> getSkillsByPersonalInfoId(@PathVariable Long id){
        return this.skillService.findByPersonalInfoId(id);
    }

    @DeleteMapping("/{id}")
    public void deleteSkill(@PathVariable Long id){
        this.skillService.delete(id);
    }
}
