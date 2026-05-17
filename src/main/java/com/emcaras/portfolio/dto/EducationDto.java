package com.emcaras.portfolio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EducationDto {
    private Long id;
    @NotBlank(message = "El grado no debe ir vacio")
    private String degree;
    @NotBlank(message = "La institucion no puede ir vacia")
    private String institution;
    @NotNull(message = "El dia de inicio no puede ser nulo")
    @PastOrPresent(message = "El dia de inicio no puede ser en el futuro")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @NotNull(message = "El dia de fin no puede ser nulo")
    @PastOrPresent(message = "El dia de fin no puede ser en el futuro")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    @NotBlank(message = "La descripcion no puede ir vacia")
    private String description;
    @NotNull(message = "El personal info id no puede ser null")
    private Long personalInfoId;
}
