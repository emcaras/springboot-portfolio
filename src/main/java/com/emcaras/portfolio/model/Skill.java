package com.emcaras.portfolio.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Skill {
    private Long id;
    @NotBlank(message = "El nombre de la habilidad no puede estar vacio")
    private String name;
    @NotNull(message = "El porcentaje no puede ir vacio")
    @Min(value = 1, message = "El porcentaje debe er igual o mayor a 0")
    @Max(value = 100, message = "El porcentaje debe ser igual o mayor a 0")
    private Integer levelPercentage;
    @NotBlank(message = "El icono no puede estar vacio")
    private String iconClass;
    private Long personalInfoId;
}
