package br.com.ticket.master.application.port.in;

import br.com.ticket.master.application.port.in.command.CreateUserCommand;
import br.com.ticket.master.domain.model.UserDomain;

public interface CreateUserUseCase {

    UserDomain execute(CreateUserCommand command);
}
