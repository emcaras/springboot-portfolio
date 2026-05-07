package com.emcaras.portfolio.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Experience {
    private Long id;
    @NotBlank(message = "El titulo de trabajo no puede ir vacio")
    private String jobTitle;
    @NotBlank(message = "El nombre de la empresa no puede ir vacio")
    private String companyName;
    @NotNull(message = "La fecha de inicio no puede ser nula")
    @PastOrPresent(message = "La fecha no puede ser futura")
    private LocalDate startDate;
    @NotNull(message = "La fecha de fin no puede ser nula")
    @PastOrPresent(message = "La fecha no puede ser futura")
    private LocalDate endDate;
    @NotBlank(message = "La descripcion no puede estar vacia")
    private String description;

    //La validamos en el service
    private Long personalInfoId;
}
