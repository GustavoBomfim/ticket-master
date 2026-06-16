package br.com.ticket.master.infraestructure.rest.controller.mapper;

import br.com.ticket.master.application.port.in.command.CreateUserCommand;
import br.com.ticket.master.domain.model.UserDomain;
import br.com.ticket.master.infraestructure.rest.dto.request.CreateUserRequestDTO;
import br.com.ticket.master.infraestructure.rest.dto.response.UserResponseDTO;

public class UserRestMapper {

    public static CreateUserCommand createUserCommand(CreateUserRequestDTO request) {
        return new CreateUserCommand(
                request.name(),
                request.email(),
                request.password()
        );
    }


    public static UserResponseDTO toUserResponseDTO(UserDomain user) {
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

}
