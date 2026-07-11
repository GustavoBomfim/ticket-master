package br.com.ticket.master.infraestructure.persistence.adapter;

import br.com.ticket.master.application.port.out.RoleRepositoryPort;
import br.com.ticket.master.domain.model.RoleDomain;
import br.com.ticket.master.infraestructure.persistence.entity.RoleEntity;
import br.com.ticket.master.infraestructure.persistence.mapper.RolePersistenceMapper;
import br.com.ticket.master.infraestructure.persistence.repository.RoleRepositoryPanache;
import jakarta.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class RoleRepositoryPortAdapter implements RoleRepositoryPort {

    private final RoleRepositoryPanache roleRepositoryPanache;

    private static final Logger log = LoggerFactory.getLogger(RoleRepositoryPortAdapter.class);


    public RoleRepositoryPortAdapter(RoleRepositoryPanache roleRepositoryPanache) {
        this.roleRepositoryPanache = roleRepositoryPanache;
    }

    @Override
    public Optional<RoleDomain> findById(Integer id) {
        log.info("Finding role by id {}", id);

        if (id == null) {
            return Optional.empty();
        }

        Optional<RoleEntity> roleEntity = roleRepositoryPanache.findByIdOptional(id);

        return roleEntity.map(RolePersistenceMapper::toDomain);
    }

    @Override
    public List<RoleDomain> findAll() {
        log.info("Finding all roles");

        return roleRepositoryPanache.findAll().list().stream()
                .map(RolePersistenceMapper::toDomain)
                .collect(Collectors.toList());
    }



}
