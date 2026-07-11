package br.com.ticket.master.application.port.in;

import br.com.ticket.master.domain.model.RoleDomain;

public interface FindRoleByIdUseCase {
    RoleDomain execute(Integer id);
}
