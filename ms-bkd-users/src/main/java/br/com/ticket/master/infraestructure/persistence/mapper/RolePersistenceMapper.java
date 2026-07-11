package br.com.ticket.master.infraestructure.persistence.mapper;

import br.com.ticket.master.domain.model.RoleDomain;
import br.com.ticket.master.infraestructure.persistence.entity.RoleEntity;

public class RolePersistenceMapper {

    public static RoleDomain toDomain(RoleEntity roleEntity) {
        if (roleEntity == null) {
            return null;
        }

        return RoleDomain.restore(
                roleEntity.getId(),
                roleEntity.getName(),
                roleEntity.getDescription(),
                roleEntity.getCreatedAt(),
                roleEntity.getUpdatedAt()
        );
    }

    public static RoleEntity toEntity(RoleDomain domain) {

        if (domain == null) {
            return null;
        }


        return RoleEntity.restore(domain);
    }


}
