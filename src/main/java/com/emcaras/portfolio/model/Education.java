package com.emcaras.portfolio.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Education {
    private Long id;
    @NotBlank(message = "El grado no debe ir vacio")
    private String degree;
    @NotBlank(message = "La institucion no puede ir vacia")
    private String institution;
    @NotNull(message = "El dia de inicio no puede ser nulo")
    @PastOrPresent(message = "El dia de inicio no puede ser en el futuro")
    private LocalDate startDate;
    @NotNull(message = "El dia de fin no puede ser nulo")
    @PastOrPresent(message = "El dia de fin no puede ser en el futuro")
    private LocalDate endDate;
    @NotBlank(message = "La descripcion no puede ir vacia")
    private String description;

    //La validacion de la clave foranea se realiza a nivel de servicio
    private Long personalInfoId;
}
