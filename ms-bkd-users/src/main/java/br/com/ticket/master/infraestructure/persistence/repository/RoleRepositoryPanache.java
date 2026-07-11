package br.com.ticket.master.infraestructure.persistence.repository;

import br.com.ticket.master.infraestructure.persistence.entity.RoleEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RoleRepositoryPanache implements PanacheRepositoryBase<RoleEntity, Integer> {
}
