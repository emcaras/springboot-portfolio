package com.emcaras.portfolio.rest;

import com.emcaras.portfolio.dto.SkillDto;
import com.emcaras.portfolio.dto.SkillMapper;
import com.emcaras.portfolio.service.ISkillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api/skills")
@RequiredArgsConstructor
public class SkillTestController {
    private final ISkillService skillService;
    private final SkillMapper skillMapper;

    @PostMapping
    public ResponseEntity<SkillDto> saveSkill(@Valid @RequestBody SkillDto skill) {
        return new ResponseEntity<>(this.skillMapper.toDto(this.skillService.save(skillMapper.toEntity(skill))), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SkillDto>> getAllSkills() {
        return ResponseEntity.ok(skillService.findAll().stream().map(skillMapper::toDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillDto> getSkillById(@PathVariable Long id) {

        SkillDto skillDto = skillMapper.toDto(skillService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "el id " + id + " no existe")));

        return ResponseEntity.ok(skillDto);
    }

    @GetMapping("/personal-info/{id}")
    public ResponseEntity<List<SkillDto>> getSkillsByPersonalInfoId(@PathVariable Long id) {
        return ResponseEntity.ok(skillService.findByPersonalInfoId(id).stream().map(skillMapper::toDto).toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long id) {
        this.skillService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
