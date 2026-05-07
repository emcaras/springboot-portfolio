package com.emcaras.portfolio.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalInfo {
    private Long id;
    @NotBlank(message = "El nombre no puede estar vacio")
    private String firstName;
    @NotBlank(message = "El apellido no puede estar vacio")
    private String lastName;
    @NotBlank(message = "El titulo no puede estar vacio")
    private String title;
    @NotBlank(message = "La descripcion del perfil no puede estar vacia")
    private String profileDescription;
    @NotBlank(message = "La url de la imagenl del perfil no puede estar vacia")
    private String profileImageUrl;
    @Min(value = 0, message =  "La experiencia debe ser mayor o igual a 0")
    private Integer yearsOfExperience;
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no es valido")
    private String email;
    @NotBlank(message = "El celular no puede estar vacio")
    @Size(min = 10, max = 10, message = "El celular debe tener 10 digitos")
    private String phone;
    @NotBlank(message = "La url de linkedin no debe ir vacia")
    private String linkedinUrl;
    @NotBlank(message = "La url de github no debe ir vacia")
    private String githubUrl;
}
