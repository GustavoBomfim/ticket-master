package br.com.ticket.master.application.service;

import br.com.ticket.master.application.port.in.FindUserUseCase;
import br.com.ticket.master.application.port.out.UserRepositoryPort;
import br.com.ticket.master.domain.exception.ResourceNotFoundException;
import br.com.ticket.master.domain.model.UserDomain;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class FindUserService implements FindUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public FindUserService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public UserDomain execute(UUID userId) {
        return userRepositoryPort.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}