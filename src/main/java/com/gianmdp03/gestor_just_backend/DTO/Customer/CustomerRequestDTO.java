package com.gianmdp03.gestor_just_backend.dto.customer;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRequestDTO {
    @NotBlank
    private String name;

    @NotBlank
    private String lastname;

    @NotBlank
    private String phoneNumber;
}
