package br.com.ticket.master.application.service;

import br.com.ticket.master.application.port.in.FindRoleUseCase;
import br.com.ticket.master.application.port.out.RoleRepositoryPort;
import br.com.ticket.master.domain.exception.ResourceNotFoundException;
import br.com.ticket.master.domain.model.RoleDomain;
import jakarta.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


@ApplicationScoped
public class FindRoleService implements FindRoleUseCase {

    private static final Logger log = LoggerFactory.getLogger(FindRoleService.class);

    private final RoleRepositoryPort roleRepositoryPort;

    public FindRoleService(RoleRepositoryPort roleRepositoryPort) {
        this.roleRepositoryPort = roleRepositoryPort;
    }


    @Override
    public List<RoleDomain> execute(){

        List<RoleDomain> roleDomains = roleRepositoryPort.findAll();

        if (roleDomains.isEmpty()) {
            throw new ResourceNotFoundException("Roles not found.");
        }

        return roleDomains;
    }


}
