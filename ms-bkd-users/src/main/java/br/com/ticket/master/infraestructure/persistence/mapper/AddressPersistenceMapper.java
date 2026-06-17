package br.com.ticket.master.infraestructure.persistence.mapper;

import br.com.ticket.master.domain.model.AddressDomain;
import br.com.ticket.master.infraestructure.persistence.entity.AddressEntity;
import br.com.ticket.master.infraestructure.persistence.entity.UserEntity;

public class AddressPersistenceMapper {

    public static AddressEntity toEntity(AddressDomain domain, UserEntity userEntity) {
        return new AddressEntity(
                domain.getId(),
                domain.getStreet(),
                domain.getNumber(),
                domain.getCity(),
                domain.getState(),
                domain.getCountry(),
                domain.getZipcode(),
                userEntity,
                domain.getCreatedAt(),
                domain.getUpdatedAt()
        );
    }

    public static AddressDomain toDomain(AddressEntity entity) {
        return AddressDomain.restore(
                entity.getId(),
                entity.getStreet(),
                entity.getNumber(),
                entity.getCity(),
                entity.getState(),
                entity.getCountry(),
                entity.getZipcode(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}