package br.com.ticket.master.application.port.out;

import br.com.ticket.master.domain.model.UserDomain;

public interface UserRepositoryPort {
    UserDomain save(UserDomain user);
}
