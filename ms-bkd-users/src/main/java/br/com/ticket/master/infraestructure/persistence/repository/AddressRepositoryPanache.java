package br.com.ticket.master.infraestructure.persistence.repository;

import br.com.ticket.master.infraestructure.persistence.entity.AddressEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class AddressRepositoryPanache implements PanacheRepositoryBase<AddressEntity, UUID> {
}
