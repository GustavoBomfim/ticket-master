package br.com.ticket.master.application.service;

import br.com.ticket.master.application.port.in.UpdateUserUseCase;
import br.com.ticket.master.application.port.in.command.UpdateUserCommand;
import br.com.ticket.master.application.port.out.UserRepositoryPort;
import br.com.ticket.master.domain.exception.ResourceNotFoundException;
import br.com.ticket.master.domain.model.UserDomain;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class UpdateUserService implements UpdateUserUseCase {

    private static final Logger log = LoggerFactory.getLogger(UpdateUserService.class);
    private final UserRepositoryPort userRepositoryPort;

    @Inject
    public UpdateUserService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public UserDomain execute(UpdateUserCommand command) {
        log.info("Iniciando a atualização para o usuário com ID: {}", command.id());

        UserDomain existingUser = userRepositoryPort.findById(command.id())
                .orElseThrow(() -> new ResourceNotFoundException("User", command.id()));

        existingUser.updateName(command.name());
        existingUser.updateEmail(command.email());

        UserDomain updatedUser = userRepositoryPort.update(existingUser);
        log.info("Usuário com ID: {} atualizado com sucesso.", updatedUser.getId());

        return updatedUser;
    }
}