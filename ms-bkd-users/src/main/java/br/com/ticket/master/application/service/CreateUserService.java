package br.com.ticket.master.application.service;

import br.com.ticket.master.application.port.in.CreateUserUseCase;
import br.com.ticket.master.application.port.in.command.CreateUserCommand;
import br.com.ticket.master.application.port.out.UserRepositoryPort;
import br.com.ticket.master.domain.model.UserDomain;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CreateUserService implements CreateUserUseCase {

    private final UserRepositoryPort userRepositoryPort;
    @Inject
    public CreateUserService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }


    @Override
    public UserDomain execute(CreateUserCommand command) {

        UserDomain user = UserDomain.createUser(command.name(), command.email(), command.password());

        return userRepositoryPort.save(user);
    }
}
