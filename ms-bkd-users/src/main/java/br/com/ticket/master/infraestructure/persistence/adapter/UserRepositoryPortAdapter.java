package br.com.ticket.master.infraestructure.persistence.adapter;

import br.com.ticket.master.application.port.out.UserRepositoryPort;
import br.com.ticket.master.domain.exception.ResourceNotFoundException;
import br.com.ticket.master.domain.model.UserDomain;
import br.com.ticket.master.infraestructure.persistence.entity.UserEntity;
import br.com.ticket.master.infraestructure.persistence.mapper.UserPersistenceMapper;
import br.com.ticket.master.infraestructure.persistence.repository.UserRepositoryPanache;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class UserRepositoryPortAdapter implements UserRepositoryPort {

    private final UserRepositoryPanache panacheRepository;

    public UserRepositoryPortAdapter(UserRepositoryPanache panacheRepository) {
        this.panacheRepository = panacheRepository;
    }


    @Transactional
    @Override
    public UserDomain save(UserDomain user) {

        UserEntity userEntity = UserPersistenceMapper.toEntity(user);

        panacheRepository.persist(userEntity);

        return UserPersistenceMapper.toDomain(userEntity);
    }

    @Transactional
    @Override
    public Optional<UserDomain> findById(UUID id) {
        Optional<UserEntity> userEntity = panacheRepository.findByIdOptional(id);
        return userEntity.map(UserPersistenceMapper::toDomain);
    }

    @Transactional
    @Override
    public UserDomain update(UserDomain user) {
        UserEntity userEntity = panacheRepository.findByIdOptional(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", user.getId()));

        userEntity.updateName(user.getName());
        userEntity.updateEmail(user.getEmail());

        panacheRepository.persist(userEntity);

        return UserPersistenceMapper.toDomain(userEntity);
    }

}