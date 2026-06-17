package br.com.ticket.master.infraestructure.rest.dto.request;

import jakarta.validation.constraints.Size;

public record UpdateAddressRequestDTO(
        @Size(max = 150) String street,
        @Size(max = 20) String number,
        @Size(max = 50) String city,
        @Size(max = 50) String state,
        @Size(max = 50) String country,
        @Size(max = 20) String zipcode
) {}