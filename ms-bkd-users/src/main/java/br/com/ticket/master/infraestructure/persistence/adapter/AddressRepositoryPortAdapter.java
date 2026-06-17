package br.com.ticket.master.infraestructure.persistence.adapter;

import br.com.ticket.master.application.port.out.AddressRepositoryPort;
import br.com.ticket.master.domain.model.AddressDomain;
import br.com.ticket.master.infraestructure.persistence.entity.AddressEntity;
import br.com.ticket.master.infraestructure.persistence.entity.UserEntity;
import br.com.ticket.master.infraestructure.persistence.mapper.AddressPersistenceMapper;
import br.com.ticket.master.infraestructure.persistence.repository.AddressRepositoryPanache;
import br.com.ticket.master.infraestructure.persistence.repository.UserRepositoryPanache;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class AddressRepositoryPortAdapter implements AddressRepositoryPort {

    private final AddressRepositoryPanache addressRepository;
    private final UserRepositoryPanache userRepository;

    public AddressRepositoryPortAdapter(AddressRepositoryPanache addressRepository, UserRepositoryPanache userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public AddressDomain save(AddressDomain address, UUID userId) {
        UserEntity userEntity = userRepository.findByIdOptional(userId)
                .orElseThrow(() -> new RuntimeException("User not found to associate address"));
        
        AddressEntity addressEntity = AddressPersistenceMapper.toEntity(address, userEntity);
        addressRepository.persist(addressEntity);
        return AddressPersistenceMapper.toDomain(addressEntity);
    }

    @Override
    public Optional<AddressDomain> findById(UUID id) {
        return addressRepository.findByIdOptional(id).map(AddressPersistenceMapper::toDomain);
    }

    @Override
    @Transactional
    public AddressDomain update(AddressDomain address) {
        AddressEntity entity = addressRepository.findByIdOptional(address.getId())
                .orElseThrow(() -> new RuntimeException("Address not found"));
        
        entity.update(address.getStreet(), address.getNumber(), address.getCity(), address.getState(), address.getCountry(), address.getZipcode());
        addressRepository.persist(entity);
        return AddressPersistenceMapper.toDomain(entity);
    }
}