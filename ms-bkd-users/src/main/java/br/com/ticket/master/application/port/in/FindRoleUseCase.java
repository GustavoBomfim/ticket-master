package br.com.ticket.master.application.port.in;

import br.com.ticket.master.domain.model.RoleDomain;

import java.util.List;

public interface FindRoleUseCase {
    List<RoleDomain> execute();
}
