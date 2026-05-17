package com.emcaras.portfolio.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private Long id;
    @NotBlank(message = "El username no debe estar vacio")
    private String username;
    @NotBlank(message = "La password no debe estar vacia")
    private String password;
    @NotNull(message = "Enabled no debe ser null")
    private boolean enabled;

}
