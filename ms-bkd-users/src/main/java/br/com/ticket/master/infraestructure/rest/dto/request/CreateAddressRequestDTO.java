package br.com.ticket.master.infraestructure.rest.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateAddressRequestDTO(
        @NotBlank @Size(max = 150) String street,
        @NotBlank @Size(max = 20) String number,
        @NotBlank @Size(max = 50) String city,
        @Size(max = 50) String state,
        @Size(max = 50) String country,
        @NotBlank @Size(max = 20) String zipcode
) {}