package br.com.ticket.master.infraestructure.persistence.repository;

import br.com.ticket.master.infraestructure.persistence.entity.UserEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class UserRepositoryPanache implements PanacheRepositoryBase<UserEntity, UUID> {
}
