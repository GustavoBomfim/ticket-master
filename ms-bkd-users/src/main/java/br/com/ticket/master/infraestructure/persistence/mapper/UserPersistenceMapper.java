package br.com.ticket.master.infraestructure.persistence.mapper;

import br.com.ticket.master.domain.model.UserDomain;
import br.com.ticket.master.infraestructure.persistence.entity.UserEntity;

public class UserPersistenceMapper {


    public static UserEntity toEntity(UserDomain userDomain) {

        return new  UserEntity(
                userDomain.getId(),
                userDomain.getName(),
                userDomain.getEmail(),
                userDomain.getPassword(),
                null,
                null,
                userDomain.getCreatedAt(),
                userDomain.getUpdatedAt(),
                userDomain.getDeletedAt()
        );
    }

    public static UserDomain toDomain(UserEntity userEntity) {

        return UserDomain.restore(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                null,
                null,
                userEntity.getCreatedAt(),
                userEntity.getUpdatedAt(),
                userEntity.getDeletedAt()
        );
    }
}
