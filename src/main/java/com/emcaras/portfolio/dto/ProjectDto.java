package com.emcaras.portfolio.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {
    private Long id;
    @NotBlank(message = "El titulo no puede ir vacio")
    private String title;
    @NotBlank(message = "La descripcion no puede ir vacia")
    private String description;
    @NotBlank(message = "La imagen url no puede ir vacia")
    @URL(message = "Debe ser una URL valida")
    private String imageUrl;
    @NotBlank(message = "El project url no puede ir vacio")
    @URL(message = "La Url debe ser valida")
    private String projectUrl;
    @NotNull(message = "El personal info id no puede ser nulo")
    @Min(value = 0, message = "El personal info id debe ser mayor o igual a 0")
    private Long personalInfoId;
}
