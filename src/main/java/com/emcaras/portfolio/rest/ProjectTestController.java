package com.emcaras.portfolio.rest;

import com.emcaras.portfolio.dto.ProjectDto;
import com.emcaras.portfolio.dto.ProjectMapper;
import com.emcaras.portfolio.model.Project;
import com.emcaras.portfolio.service.IProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/test/project")
@RequiredArgsConstructor
public class ProjectTestController {
    private final IProjectService projectService;
    private final ProjectMapper projectMapper;

    @GetMapping
    public ResponseEntity<List<ProjectDto>> findAll(){
        return new ResponseEntity<>(this.projectService.findAll().stream()
                .map(projectMapper::toDto).toList(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProjectDto> save(@Valid @RequestBody ProjectDto projectDto){
        Project project = this.projectService.save(projectMapper.toEntity(projectDto));
        return new ResponseEntity<>(projectMapper.toDto(project), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> findById(@PathVariable Long id) {
        // 1. Buscamos el objeto y si no existe lanzamos el 404 de inmediato
        Project project = this.projectService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El id " + id + " no existe"));

        // 2. Si llegamos aquí, es que el proyecto existe. Lo mapeamos a DTO.
        ProjectDto projectDto = projectMapper.toDto(project);

        // 3. Devolvemos el DTO directo en el cuerpo de la respuesta
        return ResponseEntity.ok(projectDto);
    }

    @GetMapping("personal-info/{id}")
    public ResponseEntity<List<ProjectDto>> findByPersonalInfoId(@PathVariable Long id){
        List<ProjectDto> project = this.projectService.findByPersonalInfoId(id)
                .stream().map(projectMapper::toDto).toList();
        return ResponseEntity.ok(project);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.projectService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
