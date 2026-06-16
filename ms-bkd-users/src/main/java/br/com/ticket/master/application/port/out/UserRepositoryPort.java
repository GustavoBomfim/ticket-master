package br.com.ticket.master.application.port.out;

import br.com.ticket.master.domain.model.UserDomain;
import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryPort {
    UserDomain save(UserDomain user);

    Optional<UserDomain> findById(UUID id);
}
