package br.com.ticket.master.application.port.in;

import br.com.ticket.master.domain.model.UserDomain;

import java.util.UUID;

public interface FindUserUseCase {
    UserDomain execute(UUID userId);
}
