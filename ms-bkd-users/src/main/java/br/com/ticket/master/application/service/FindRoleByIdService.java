package br.com.ticket.master.application.service;

import br.com.ticket.master.application.port.in.FindRoleByIdUseCase;
import br.com.ticket.master.application.port.out.RoleRepositoryPort;
import br.com.ticket.master.domain.exception.ResourceNotFoundException;
import br.com.ticket.master.domain.model.RoleDomain;
import jakarta.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class FindRoleByIdService implements FindRoleByIdUseCase {


    private static final Logger log = LoggerFactory.getLogger(FindRoleByIdService.class);

    private final RoleRepositoryPort roleRepositoryPort;

    public FindRoleByIdService(RoleRepositoryPort roleRepositoryPort) {
        this.roleRepositoryPort = roleRepositoryPort;
    }

    @Override
    public RoleDomain execute(Integer id){

        return roleRepositoryPort.findById(id)
                .orElseThrow(() -> {
                    log.error("Role with id: {} not found.", id);
                    return new ResourceNotFoundException("Role with id: " + id + " not found");
                });


    }


}
