package br.com.ticket.master.infraestructure.rest.controller.mapper;

import br.com.ticket.master.domain.model.AddressDomain;
import br.com.ticket.master.infraestructure.rest.dto.response.AddressResponseDTO;

public class AddressRestMapper {

    public static AddressResponseDTO toResponse(AddressDomain domain) {
        return new AddressResponseDTO(
                domain.getId(),
                domain.getStreet(),
                domain.getNumber(),
                domain.getCity(),
                domain.getState(),
                domain.getCountry(),
                domain.getZipcode()
        );
    }
}