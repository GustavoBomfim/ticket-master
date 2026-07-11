package br.com.ticket.master.infraestructure.rest.controller.mapper;

import br.com.ticket.master.domain.model.RoleDomain;
import br.com.ticket.master.infraestructure.rest.dto.response.RoleResponseDTO;

public class RoleRestMapper {

    public static RoleResponseDTO toResponse(RoleDomain roleDomain) {

        if (roleDomain == null) {
            return null;
        }

        return new RoleResponseDTO(roleDomain.getId(), roleDomain.getName(),  roleDomain.getDescription());
    }

}
