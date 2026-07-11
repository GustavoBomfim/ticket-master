package br.com.ticket.master.application.port.out;

import br.com.ticket.master.domain.model.RoleDomain;

import java.util.List;
import java.util.Optional;

public interface RoleRepositoryPort {
    Optional<RoleDomain> findById(Integer id);

    List<RoleDomain> findAll();
}
