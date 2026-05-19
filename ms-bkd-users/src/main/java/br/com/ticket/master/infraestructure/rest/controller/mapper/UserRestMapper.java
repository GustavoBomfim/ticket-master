package br.com.ticket.master.infraestructure.rest.controller.mapper;

import br.com.ticket.master.application.port.in.command.CreateUserCommand;
import br.com.ticket.master.infraestructure.rest.dto.request.CreateUserRequestDTO;

public class UserRestMapper {

    public static CreateUserCommand createUserCommand(CreateUserRequestDTO request) {
        return new CreateUserCommand(
                request.name(),
                request.email(),
                request.password()
        );
    }

}
