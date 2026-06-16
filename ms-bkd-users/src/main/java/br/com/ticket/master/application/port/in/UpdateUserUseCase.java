package br.com.ticket.master.application.port.in;

import br.com.ticket.master.application.port.in.command.UpdateUserCommand;
import br.com.ticket.master.domain.model.UserDomain;

public interface UpdateUserUseCase {

    UserDomain execute(UpdateUserCommand command);
}
